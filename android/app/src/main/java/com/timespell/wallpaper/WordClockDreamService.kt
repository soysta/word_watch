package com.timespell.wallpaper

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.service.dreams.DreamService
import android.view.View
import java.util.Calendar

class WordClockDreamService : DreamService() {

    private val prefs by lazy {
        getSharedPreferences("timespell_prefs", MODE_PRIVATE)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isInteractive = false
        isFullscreen = true
        isScreenBright = false

        val clockView = ClockView()
        setContentView(clockView)
        clockView.startUpdating()
    }

    private inner class ClockView : View(this@WordClockDreamService) {
        private val handler = Handler(Looper.getMainLooper())
        private val updater = Runnable { updateAndDraw() }

        fun startUpdating() {
            handler.post(updater)
        }

        fun stopUpdating() {
            handler.removeCallbacks(updater)
        }

        private fun updateAndDraw() {
            invalidate()
            handler.postDelayed(updater, 15000)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            val lang = prefs.getString("language", "tr") ?: "tr"
            val themeIdx = prefs.getInt("theme", 1)

            val theme = WordClockThemes.getTheme(themeIdx)
            val grid = if (lang == "tr") WordClockData.GRID_TR else WordClockData.GRID_EN
            val activePositions = getActivePositions(lang)

            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()

            val bgPaint = Paint().apply { color = theme.bgColor }
            canvas.drawRect(0f, 0f, w, h, bgPaint)

            val cols = grid[0].length
            val rows = grid.size

            val cellSize = minOf(w / cols, h * 0.7f / rows)
            val gridW = cellSize * cols
            val gridH = cellSize * rows
            val offsetX = (w - gridW) / 2f
            val offsetY = (h - gridH) / 2f

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
            val hr = cal.get(Calendar.HOUR_OF_DAY)
            val min = cal.get(Calendar.MINUTE)
            val roundedM = (Math.round(min / 5.0) * 5).toInt()

            return if (lang == "tr") {
                WordClockLogic.getActiveTR(hr, roundedM)
            } else {
                WordClockLogic.getActiveEN(hr, roundedM)
            }
        }

        override fun onDetachedFromWindow() {
            stopUpdating()
            super.onDetachedFromWindow()
        }
    }
}
