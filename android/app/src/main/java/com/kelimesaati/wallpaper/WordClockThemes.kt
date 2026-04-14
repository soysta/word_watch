package com.kelimesaati.wallpaper

import android.graphics.Color

data class ClockTheme(
    val name: String,
    val bgColor: Int,
    val offColor: Int,
    val onColor: Int,
    val glowRadius: Float = 0f,
    val glowColor: Int = Color.TRANSPARENT,
    val fontFamily: String = "sans-serif"
)

object WordClockThemes {
    private val themes = listOf(
        ClockTheme("Klasik", Color.parseColor("#dcd7c9"), Color.parseColor("#333c3c3c"), Color.parseColor("#2c2c2c"), fontFamily = "serif"),
        ClockTheme("Gece", Color.parseColor("#0c0c1e"), Color.parseColor("#33648CFF"), Color.parseColor("#60a0ff"), 10f, Color.parseColor("#8064A0FF"), "monospace"),
        ClockTheme("Okyanus", Color.parseColor("#003366"), Color.parseColor("#6696C8FF"), Color.parseColor("#40e0ff"), 8f, Color.parseColor("#9940E0FF")),
        ClockTheme("Neon", Color.parseColor("#0a0a0a"), Color.parseColor("#33FF00FF"), Color.parseColor("#FF00FF"), 15f, Color.parseColor("#CCFF00FF"), "monospace"),
        ClockTheme("Retro", Color.parseColor("#2b2b2b"), Color.parseColor("#3300FF00"), Color.parseColor("#00FF00"), 8f, Color.parseColor("#8000FF00"), "monospace"),
        ClockTheme("Pastel", Color.parseColor("#fce4ec"), Color.parseColor("#33e91e63"), Color.parseColor("#e91e63")),
        ClockTheme("Antik", Color.parseColor("#3e2723"), Color.parseColor("#33d7ccc8"), Color.parseColor("#d7ccc8"), fontFamily = "serif"),
        ClockTheme("Uzay", Color.parseColor("#000022"), Color.parseColor("#336644ff"), Color.parseColor("#BB86FC"), 12f, Color.parseColor("#99BB86FC")),
        ClockTheme("Renkli", Color.parseColor("#1a1a2e"), Color.parseColor("#33e94560"), Color.parseColor("#e94560"), 6f, Color.parseColor("#99e94560")),
        ClockTheme("Matrix", Color.parseColor("#000000"), Color.parseColor("#3300ff41"), Color.parseColor("#00ff41"), 10f, Color.parseColor("#CC00ff41"), "monospace"),
        ClockTheme("Güneş", Color.parseColor("#ff6f00"), Color.parseColor("#33fff3e0"), Color.parseColor("#fff3e0"), 8f, Color.parseColor("#99fff3e0")),
        ClockTheme("Mor Gece", Color.parseColor("#12005e"), Color.parseColor("#33b388ff"), Color.parseColor("#b388ff"), 10f, Color.parseColor("#99b388ff")),
        ClockTheme("Kan Kırmızı", Color.parseColor("#1a0000"), Color.parseColor("#33ff1744"), Color.parseColor("#ff1744"), 12f, Color.parseColor("#CCff1744")),
        ClockTheme("Altın Lüx", Color.parseColor("#1a1a0a"), Color.parseColor("#33ffd700"), Color.parseColor("#FFD700"), 8f, Color.parseColor("#99FFD700"), "serif"),
        ClockTheme("Buz Mavisi", Color.parseColor("#e3f2fd"), Color.parseColor("#3301579b"), Color.parseColor("#01579b")),
        ClockTheme("Çimen", Color.parseColor("#1b5e20"), Color.parseColor("#33a5d6a7"), Color.parseColor("#a5d6a7"), 6f, Color.parseColor("#99a5d6a7")),
        ClockTheme("Siberpunk", Color.parseColor("#0d0221"), Color.parseColor("#33ff6ec7"), Color.parseColor("#ff6ec7"), 12f, Color.parseColor("#CCff6ec7"), "monospace"),
        ClockTheme("Pembe Şeker", Color.parseColor("#fce4ec"), Color.parseColor("#33f06292"), Color.parseColor("#f06292")),
        ClockTheme("Turuncu", Color.parseColor("#1a0a00"), Color.parseColor("#33ff9100"), Color.parseColor("#ff9100"), 8f, Color.parseColor("#99ff9100")),
        ClockTheme("Gri Ton", Color.parseColor("#212121"), Color.parseColor("#33757575"), Color.parseColor("#e0e0e0")),
        ClockTheme("Tropikal", Color.parseColor("#004d40"), Color.parseColor("#3364ffda"), Color.parseColor("#64ffda"), 8f, Color.parseColor("#9964ffda")),
        ClockTheme("Vintage", Color.parseColor("#3e2723"), Color.parseColor("#33bcaaa4"), Color.parseColor("#bcaaa4"), fontFamily = "serif"),
        ClockTheme("Elektrik", Color.parseColor("#0a0a2e"), Color.parseColor("#3300e5ff"), Color.parseColor("#00e5ff"), 15f, Color.parseColor("#CC00e5ff"), "monospace"),
        ClockTheme("Ahşap", Color.parseColor("#4e342e"), Color.parseColor("#33d7ccc8"), Color.parseColor("#efebe9"), fontFamily = "serif"),
        ClockTheme("Piksel", Color.parseColor("#000000"), Color.parseColor("#3376ff03"), Color.parseColor("#76ff03"), 6f, Color.parseColor("#9976ff03"), "monospace"),
        ClockTheme("Gökküşağı", Color.parseColor("#1a1a1a"), Color.parseColor("#33ffffff"), Color.parseColor("#ff4081"), 10f, Color.parseColor("#99ff4081")),
        ClockTheme("Kara Mod", Color.parseColor("#000000"), Color.parseColor("#33ffffff"), Color.parseColor("#ffffff")),
        ClockTheme("Deniz", Color.parseColor("#006064"), Color.parseColor("#3380deea"), Color.parseColor("#80deea"), 8f, Color.parseColor("#9980deea")),
        ClockTheme("Kızıl Gezegen", Color.parseColor("#1a0500"), Color.parseColor("#33ff3d00"), Color.parseColor("#ff3d00"), 10f, Color.parseColor("#99ff3d00")),
        ClockTheme("Klasik Koyu", Color.parseColor("#1a1a16"), Color.parseColor("#33f4f1ea"), Color.parseColor("#f4f1ea"), fontFamily = "serif")
    )

    fun getTheme(index: Int): ClockTheme = themes.getOrElse(index) { themes[1] }
    fun getThemeNames(): List<String> = themes.map { it.name }
    fun count(): Int = themes.size
}
