package com.timespell.wear

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import java.util.Calendar

class WordClockWatchFaceService : WallpaperService() {
    override fun onCreateEngine(): Engine = WatchFaceEngine()

    inner class WatchFaceEngine : WallpaperService.Engine() {
        private val handler = Handler(Looper.getMainLooper())
        private val drawRunner = Runnable { draw() }
        private var visible = false

        // Compact Turkish grid for round watch face
        private val GRID = arrayOf(
            "SAATXXXXX",
            "BİRİKİÜÇÜ",
            "DÖRTBEŞİX",
            "ALTIYEDIX",
            "SEKİDOKUZ",
            "ONXONBİRX",
            "ONİKİXXXX",
            "BUÇÇEYVAR",
            "YİRMİONBŞ",
            "XXGEÇXYOR"
        )

        // Simplified word positions for watch
        private val WORDS = mapOf(
            "SAAT" to listOf(0 to 0, 0 to 1, 0 to 2, 0 to 3),
            "BİR" to listOf(1 to 0, 1 to 1, 1 to 2),
            "İKİ" to listOf(1 to 3, 1 to 4, 1 to 5),
            "ÜÇ" to listOf(1 to 6, 1 to 7),
            "DÖRT" to listOf(2 to 0, 2 to 1, 2 to 2, 2 to 3),
            "BEŞ_H" to listOf(2 to 4, 2 to 5, 2 to 6),
            "ALTI" to listOf(3 to 0, 3 to 1, 3 to 2, 3 to 3),
            "YEDİ" to listOf(3 to 4, 3 to 5, 3 to 6, 3 to 7),
            "SEKİZ" to listOf(4 to 0, 4 to 1, 4 to 2, 4 to 3),
            "DOKUZ" to listOf(4 to 4, 4 to 5, 4 to 6, 4 to 7, 4 to 8),
            "ON_H" to listOf(5 to 0, 5 to 1),
            "ONBİR" to listOf(5 to 3, 5 to 4, 5 to 5, 5 to 6, 5 to 7),
            "ONİKİ" to listOf(6 to 0, 6 to 1, 6 to 2, 6 to 3, 6 to 4),
            "BUÇUK" to listOf(7 to 0, 7 to 1, 7 to 2),
            "ÇEYREK" to listOf(7 to 3, 7 to 4, 7 to 5),
            "VAR" to listOf(7 to 6, 7 to 7, 7 to 8),
            "YİRMİ" to listOf(8 to 0, 8 to 1, 8 to 2, 8 to 3, 8 to 4),
            "ON_M" to listOf(8 to 5, 8 to 6),
            "BEŞ_M" to listOf(8 to 7, 8 to 8),
            "GEÇİYOR" to listOf(9 to 2, 9 to 3, 9 to 4, 9 to 5, 9 to 6, 9 to 7, 9 to 8)
        )

        private val HOUR_KEYS = arrayOf("ONİKİ","BİR","İKİ","ÜÇ","DÖRT","BEŞ_H","ALTI","YEDİ","SEKİZ","DOKUZ","ON_H","ONBİR","ONİKİ")

        override fun onVisibilityChanged(isVisible: Boolean) {
            visible = isVisible
            if (isVisible) handler.post(drawRunner) else handler.removeCallbacks(drawRunner)
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
                if (canvas != null) drawWatchFace(canvas)
            } finally {
                if (canvas != null) holder.unlockCanvasAndPost(canvas)
            }
            handler.removeCallbacks(drawRunner)
            if (visible) handler.postDelayed(drawRunner, 15000)
        }

        private fun drawWatchFace(canvas: Canvas) {
            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()
            val cx = w / 2f
            val cy = h / 2f

            // Dark background
            canvas.drawColor(Color.parseColor("#0c0c1e"))

            // Circular border
            val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#60a0ff")
                style = Paint.Style.STROKE
                strokeWidth = 3f
            }
            canvas.drawCircle(cx, cy, minOf(cx, cy) - 4f, borderPaint)

            val cal = Calendar.getInstance()
            val hour24 = cal.get(Calendar.HOUR_OF_DAY)
            var dHour = hour24 % 12
            var dMin = (Math.round(cal.get(Calendar.MINUTE) / 5.0) * 5).toInt()
            if (dMin == 60) { dMin = 0; dHour = (dHour + 1) % 12 }
            val nHour = (dHour + 1) % 12

            val activeKeys = mutableSetOf("SAAT")
            when {
                dMin == 0 -> activeKeys.add(HOUR_KEYS[dHour])
                dMin == 30 -> { activeKeys.add(HOUR_KEYS[dHour]); activeKeys.add("BUÇUK") }
                dMin <= 25 -> {
                    activeKeys.add(HOUR_KEYS[dHour])
                    when (dMin) { 5 -> activeKeys.add("BEŞ_M"); 10 -> activeKeys.add("ON_M"); 15 -> activeKeys.add("ÇEYREK"); 20 -> activeKeys.add("YİRMİ"); 25 -> { activeKeys.add("YİRMİ"); activeKeys.add("BEŞ_M") } }
                    activeKeys.add("GEÇİYOR")
                }
                else -> {
                    activeKeys.add(HOUR_KEYS[nHour])
                    val rem = 60 - dMin
                    when (rem) { 5 -> activeKeys.add("BEŞ_M"); 10 -> activeKeys.add("ON_M"); 15 -> activeKeys.add("ÇEYREK"); 20 -> activeKeys.add("YİRMİ"); 25 -> { activeKeys.add("YİRMİ"); activeKeys.add("BEŞ_M") } }
                    activeKeys.add("VAR")
                }
            }

            val activePos = mutableSetOf<Pair<Int, Int>>()
            for (key in activeKeys) { WORDS[key]?.let { activePos.addAll(it) } }

            val rows = GRID.size
            val cols = GRID[0].length
            val cellSize = minOf(w * 0.8f / cols, h * 0.7f / rows)
            val gridW = cellSize * cols
            val gridH = cellSize * rows
            val offsetX = (w - gridW) / 2f
            val offsetY = (h - gridH) / 2f

            val offPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#1a3060")
                textSize = cellSize * 0.55f
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
            }

            val onPaint = Paint(offPaint).apply {
                color = Color.parseColor("#60a0ff")
                isFakeBoldText = true
                setShadowLayer(8f, 0f, 0f, Color.parseColor("#8064A0FF"))
            }

            for (r in GRID.indices) {
                for (c in GRID[r].indices) {
                    val ch = GRID[r][c]
                    if (ch == 'X') continue
                    val isOn = activePos.contains(Pair(r, c))
                    val paint = if (isOn) onPaint else offPaint
                    val x = offsetX + c * cellSize + cellSize / 2f
                    val y = offsetY + r * cellSize + cellSize / 2f + cellSize * 0.18f
                    canvas.drawText(ch.toString(), x, y, paint)
                }
            }
        }
    }
}
