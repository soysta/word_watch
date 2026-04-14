package com.timespell.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.WindowManager
import java.util.Calendar

class WordClockWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine = WordClockEngine()

    inner class WordClockEngine : WallpaperService.Engine() {
        private val handler = Handler(Looper.getMainLooper())
        private val drawRunner = Runnable { draw() }
        private var visible = false
        private var lastLockScreenMinKey = ""

        private val prefs by lazy {
            getSharedPreferences("timespell_prefs", MODE_PRIVATE)
        }

        override fun onVisibilityChanged(isVisible: Boolean) {
            visible = isVisible
            if (isVisible) {
                handler.post(drawRunner)
            } else {
                handler.removeCallbacks(drawRunner)
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            visible = false
            handler.removeCallbacks(drawRunner)
            super.onSurfaceDestroyed(holder)
        }

        private fun draw() {
            val holder = surfaceHolder
            var canvas: Canvas? = null
            try {
                canvas = holder.lockCanvas()
                if (canvas != null) {
                    drawClock(canvas)
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas)
                }
            }
            // Kilit ekranını güncelle (sadece saat değiştiğinde)
            updateLockScreenIfNeeded()

            handler.removeCallbacks(drawRunner)
            if (visible) {
                handler.postDelayed(drawRunner, 15000) // 15 saniyede bir güncelle
            }
        }

        private fun drawClock(canvas: Canvas, pushDown: Boolean = false) {
            val lang = prefs.getString("language", "tr") ?: "tr"
            val themeIdx = prefs.getInt("theme", 1) // varsayilan: Gece

            val theme = WordClockThemes.getTheme(themeIdx)
            val grid = if (lang == "tr") WordClockData.GRID_TR else WordClockData.GRID_EN
            val activePositions = getActivePositions(lang)

            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()

            // Arka plan
            val bgPaint = Paint().apply { color = theme.bgColor }
            canvas.drawRect(0f, 0f, w, h, bgPaint)

            val cols = grid[0].length
            val rows = grid.size

            // Kare alan hesapla (ortaya yerleştir)
            val cellSize = minOf(w / cols, h * 0.7f / rows)
            val gridW = cellSize * cols
            val gridH = cellSize * rows
            val offsetX = (w - gridW) / 2f
            // Kilit ekranı modunda grid'i aşağı kaydır (Android saatinin altına)
            val offsetY = if (pushDown) {
                h * 0.38f // Üstten %38 aşağıda başla — Android saat alanının altı
            } else {
                (h - gridH) / 2f
            }

            val offPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = theme.offColor
                textSize = cellSize * 0.55f
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(theme.fontFamily, Typeface.NORMAL)
            }

            val onPaint = Paint(offPaint).apply {
                color = theme.onColor
                isFakeBoldText = true
                if (theme.glowRadius > 0) {
                    setShadowLayer(theme.glowRadius, 0f, 0f, theme.glowColor)
                }
            }

            for (r in grid.indices) {
                val row = grid[r]
                for (c in row.indices) {
                    val isOn = activePositions.contains(Pair(r, c))
                    val paint = if (isOn) onPaint else offPaint
                    val x = offsetX + c * cellSize + cellSize / 2f
                    val y = offsetY + r * cellSize + cellSize / 2f + cellSize * 0.18f
                    canvas.drawText(row[c].toString(), x, y, paint)
                }
            }
        }

        private fun getActivePositions(lang: String): Set<Pair<Int, Int>> {
            val cal = Calendar.getInstance()
            val h = cal.get(Calendar.HOUR_OF_DAY)
            val m = cal.get(Calendar.MINUTE)
            val roundedM = (Math.round(m / 5.0) * 5).toInt()

            return if (lang == "tr") {
                WordClockLogic.getActiveTR(h, roundedM)
            } else {
                WordClockLogic.getActiveEN(h, roundedM)
            }
        }

        private fun updateLockScreenIfNeeded() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return
            if (!prefs.getBoolean("lock_screen", false)) return

            val cal = Calendar.getInstance()
            val h = cal.get(Calendar.HOUR_OF_DAY)
            val m = cal.get(Calendar.MINUTE)
            val roundedM = (Math.round(m / 5.0) * 5).toInt()
            val key = "$h:$roundedM:${prefs.getInt("theme", 1)}:${prefs.getString("language", "tr")}"

            if (key == lastLockScreenMinKey) return
            lastLockScreenMinKey = key

            try {
                val wm = getSystemService(WINDOW_SERVICE) as WindowManager
                val metrics = DisplayMetrics()
                @Suppress("DEPRECATION")
                wm.defaultDisplay.getRealMetrics(metrics)
                val w = metrics.widthPixels
                val h2 = metrics.heightPixels

                val bitmap = Bitmap.createBitmap(w, h2, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawClock(canvas, pushDown = true)

                val wallpaperManager = WallpaperManager.getInstance(this@WordClockWallpaperService)
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                bitmap.recycle()
            } catch (e: Exception) {
                // Sessizce devam et
            }
        }
    }
}
