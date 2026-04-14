package com.timespell.wear

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.view.SurfaceHolder
import androidx.wear.watchface.CanvasType
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.Renderer
import androidx.wear.watchface.WatchFace
import androidx.wear.watchface.WatchFaceService
import androidx.wear.watchface.WatchFaceType
import androidx.wear.watchface.WatchState
import androidx.wear.watchface.style.CurrentUserStyleRepository
import androidx.wear.watchface.style.UserStyleSchema
import androidx.wear.watchface.style.UserStyleSetting
import androidx.wear.watchface.style.WatchFaceLayer
import java.time.ZonedDateTime
import kotlin.math.sqrt

data class WearTheme(val id: String, val name: String, val onColor: String, val offColor: String, val bgColor: String, val font: String)

class WordClockWatchFaceService : WatchFaceService() {

    companion object {
        val COLOR_STYLE_ID = UserStyleSetting.Id("color_style")

        val THEMES = arrayOf(
            WearTheme("night",    "Gece",       "#60a0ff", "#1a3060", "#0c0c1e",  "monospace"),
            WearTheme("ocean",    "Okyanus",    "#40e0ff", "#0a3050", "#003366",  "sans-serif"),
            WearTheme("neon",     "Neon",       "#ff00ff", "#301030", "#0a0a0a",  "sans-serif-condensed"),
            WearTheme("retro",    "Retro",      "#00ff88", "#0a2018", "#2d1b39",  "monospace"),
            WearTheme("matrix",   "Matrix",     "#00ff00", "#003300", "#000000",  "monospace"),
            WearTheme("gold",     "Altın",      "#ffd700", "#302810", "#1a1a1a",  "serif"),
            WearTheme("blood",    "Kırmızı",    "#ff4444", "#301010", "#1a0000",  "sans-serif-medium"),
            WearTheme("purple",   "Mor Gece",   "#da70d6", "#201040", "#2c003e",  "sans-serif-light"),
            WearTheme("cyber",    "Siberpunk",  "#00f5ff", "#0a1530", "#0d0221",  "sans-serif-condensed"),
            WearTheme("orange",   "Turuncu",    "#ffcc00", "#302010", "#1a0e00",  "sans-serif"),
            WearTheme("grey",     "Gri Ton",    "#ffffff", "#333333", "#212121",  "sans-serif-light"),
            WearTheme("electric", "Elektrik",   "#0066ff", "#0a1040", "#000033",  "monospace"),
        )
    }

    override fun createUserStyleSchema(): UserStyleSchema {
        val colorOptions = THEMES.map { theme ->
            UserStyleSetting.ListUserStyleSetting.ListOption(
                UserStyleSetting.Option.Id(theme.id),
                theme.name,
                theme.name,
                null
            )
        }
        val colorSetting = UserStyleSetting.ListUserStyleSetting(
            id = COLOR_STYLE_ID,
            displayName = "Theme",
            description = "Choose theme",
            icon = null,
            options = colorOptions,
            affectsWatchFaceLayers = listOf(WatchFaceLayer.BASE)
        )
        return UserStyleSchema(listOf(colorSetting))
    }

    override fun createComplicationSlotsManager(
        currentUserStyleRepository: CurrentUserStyleRepository
    ): ComplicationSlotsManager = ComplicationSlotsManager(emptyList(), currentUserStyleRepository)

    override suspend fun createWatchFace(
        surfaceHolder: SurfaceHolder,
        watchState: WatchState,
        complicationSlotsManager: ComplicationSlotsManager,
        currentUserStyleRepository: CurrentUserStyleRepository
    ): WatchFace {
        val renderer = WordClockRenderer(
            surfaceHolder = surfaceHolder,
            watchState = watchState,
            currentUserStyleRepository = currentUserStyleRepository
        )
        return WatchFace(WatchFaceType.DIGITAL, renderer)
    }

    class WordClockRenderer(
        surfaceHolder: SurfaceHolder,
        watchState: WatchState,
        currentUserStyleRepository: CurrentUserStyleRepository
    ) : Renderer.CanvasRenderer2<Renderer.SharedAssets>(
        surfaceHolder = surfaceHolder,
        currentUserStyleRepository = currentUserStyleRepository,
        watchState = watchState,
        canvasType = CanvasType.SOFTWARE,
        interactiveDrawModeUpdateDelayMillis = 60000L,
        clearWithBackgroundTintBeforeRenderingHighlightLayer = true
    ) {
        class EmptySharedAssets : SharedAssets {
            override fun onDestroy() {}
        }

        override suspend fun createSharedAssets(): SharedAssets = EmptySharedAssets()

        // Compact 10x9 English word clock grid
        private val GRID = arrayOf(
            "ITXISXHALF",  // 0
            "QUARTERTEN",  // 1
            "TWENTYFIVE",  // 2
            "PASTOXFOUR",  // 3
            "TWONESEVEN",  // 4
            "THREELEVEN",  // 5
            "FIVETENINE",  // 6
            "EIGHTWELVE",  // 7
            "SIXXAMPMXX"   // 8
        )

        private val WORDS = mapOf(
            "IT" to listOf(0 to 0, 0 to 1),
            "IS" to listOf(0 to 3, 0 to 4),
            "HALF" to listOf(0 to 6, 0 to 7, 0 to 8, 0 to 9),
            "QUARTER" to listOf(1 to 0, 1 to 1, 1 to 2, 1 to 3, 1 to 4, 1 to 5, 1 to 6),
            "TEN_M" to listOf(1 to 7, 1 to 8, 1 to 9),
            "TWENTY" to listOf(2 to 0, 2 to 1, 2 to 2, 2 to 3, 2 to 4, 2 to 5),
            "FIVE_M" to listOf(2 to 6, 2 to 7, 2 to 8, 2 to 9),
            "PAST" to listOf(3 to 0, 3 to 1, 3 to 2, 3 to 3),
            "TO" to listOf(3 to 3, 3 to 4),
            "FOUR" to listOf(3 to 6, 3 to 7, 3 to 8, 3 to 9),
            "TWO" to listOf(4 to 0, 4 to 1, 4 to 2),
            "ONE" to listOf(4 to 2, 4 to 3, 4 to 4),
            "SEVEN" to listOf(4 to 5, 4 to 6, 4 to 7, 4 to 8, 4 to 9),
            "THREE" to listOf(5 to 0, 5 to 1, 5 to 2, 5 to 3, 5 to 4),
            "ELEVEN" to listOf(5 to 4, 5 to 5, 5 to 6, 5 to 7, 5 to 8, 5 to 9),
            "FIVE_H" to listOf(6 to 0, 6 to 1, 6 to 2, 6 to 3),
            "TEN_H" to listOf(6 to 4, 6 to 5, 6 to 6),
            "NINE" to listOf(6 to 6, 6 to 7, 6 to 8, 6 to 9),
            "EIGHT" to listOf(7 to 0, 7 to 1, 7 to 2, 7 to 3, 7 to 4),
            "TWELVE" to listOf(7 to 4, 7 to 5, 7 to 6, 7 to 7, 7 to 8, 7 to 9),
            "SIX" to listOf(8 to 0, 8 to 1, 8 to 2),
            "AM" to listOf(8 to 4, 8 to 5),
            "PM" to listOf(8 to 6, 8 to 7)
        )

        private val HOUR_KEYS = arrayOf("TWELVE","ONE","TWO","THREE","FOUR","FIVE_H","SIX","SEVEN","EIGHT","NINE","TEN_H","ELEVEN","TWELVE")

        private val styleRepo = currentUserStyleRepository

        private fun getTheme(): WearTheme {
            val style = styleRepo.userStyle.value
            val opt = style[COLOR_STYLE_ID] as? UserStyleSetting.ListUserStyleSetting.ListOption
            val id = opt?.id?.toString() ?: "night"
            return THEMES.find { it.id == id } ?: THEMES[0]
        }

        override fun render(
            canvas: Canvas,
            bounds: Rect,
            zonedDateTime: ZonedDateTime,
            sharedAssets: SharedAssets
        ) {
            val w = bounds.width().toFloat()
            val h = bounds.height().toFloat()
            val cx = w / 2f
            val cy = h / 2f
            val radius = minOf(cx, cy)

            val theme = getTheme()
            val onColor = Color.parseColor(theme.onColor)
            val offColor = Color.parseColor(theme.offColor)

            // Theme background
            canvas.drawColor(Color.parseColor(theme.bgColor))

            val hour24 = zonedDateTime.hour
            var dHour = hour24 % 12
            var dMin = (Math.round(zonedDateTime.minute / 5.0) * 5).toInt()
            if (dMin == 60) { dMin = 0; dHour = (dHour + 1) % 12 }
            val nHour = (dHour + 1) % 12

            val activeKeys = mutableSetOf("IT", "IS")
            if (hour24 < 12) activeKeys.add("AM") else activeKeys.add("PM")

            when {
                dMin == 0 -> {
                    activeKeys.add(HOUR_KEYS[dHour])
                }
                dMin == 30 -> {
                    activeKeys.add("HALF")
                    activeKeys.add("PAST")
                    activeKeys.add(HOUR_KEYS[dHour])
                }
                dMin < 30 -> {
                    when (dMin) {
                        5 -> activeKeys.add("FIVE_M")
                        10 -> activeKeys.add("TEN_M")
                        15 -> activeKeys.add("QUARTER")
                        20 -> activeKeys.add("TWENTY")
                        25 -> { activeKeys.add("TWENTY"); activeKeys.add("FIVE_M") }
                    }
                    activeKeys.add("PAST")
                    activeKeys.add(HOUR_KEYS[dHour])
                }
                else -> {
                    val rem = 60 - dMin
                    when (rem) {
                        5 -> activeKeys.add("FIVE_M")
                        10 -> activeKeys.add("TEN_M")
                        15 -> activeKeys.add("QUARTER")
                        20 -> activeKeys.add("TWENTY")
                        25 -> { activeKeys.add("TWENTY"); activeKeys.add("FIVE_M") }
                    }
                    activeKeys.add("TO")
                    activeKeys.add(HOUR_KEYS[nHour])
                }
            }

            val activePos = mutableSetOf<Pair<Int, Int>>()
            for (key in activeKeys) { WORDS[key]?.let { activePos.addAll(it) } }

            val rows = GRID.size    // 9
            val cols = 10
            // Inscribe grid rectangle inside circle with padding
            val padding = 0.90f
            val diag = sqrt((cols.toFloat() / 2f) * (cols.toFloat() / 2f) + (rows.toFloat() / 2f) * (rows.toFloat() / 2f))
            val cellSize = (radius * padding) / diag
            val gridW = cellSize * cols
            val gridH = cellSize * rows
            val offsetX = (w - gridW) / 2f
            val offsetY = (h - gridH) / 2f

            val offPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = offColor
                textSize = cellSize * 0.62f
                textAlign = Paint.Align.CENTER
                typeface = Typeface.create(theme.font, Typeface.BOLD)
            }

            val onPaint = Paint(offPaint).apply {
                color = onColor
                setShadowLayer(8f, 0f, 0f, onColor and 0x80FFFFFF.toInt())
            }

            for (r in GRID.indices) {
                for (c in GRID[r].indices) {
                    val ch = GRID[r][c]
                    if (ch == '_' || ch == '-') continue
                    val isOn = activePos.contains(Pair(r, c))
                    val paint = if (isOn) onPaint else offPaint
                    val x = offsetX + c * cellSize + cellSize / 2f
                    val y = offsetY + r * cellSize + cellSize / 2f + cellSize * 0.2f
                    canvas.drawText(ch.toString(), x, y, paint)
                }
            }
        }

        override fun renderHighlightLayer(
            canvas: Canvas,
            bounds: Rect,
            zonedDateTime: ZonedDateTime,
            sharedAssets: SharedAssets
        ) {
            canvas.drawColor(Color.argb(64, 0, 0, 0))
        }
    }
}
