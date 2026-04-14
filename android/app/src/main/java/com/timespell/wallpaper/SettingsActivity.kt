package com.timespell.wallpaper

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.view.Gravity
import android.util.TypedValue

class SettingsActivity : Activity() {

    private val prefs by lazy {
        getSharedPreferences("timespell_prefs", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(48, 96, 48, 48)
            setBackgroundColor(0xFF1a1a2e.toInt())
        }

        // Title
        val title = TextView(this).apply {
            text = "⏰ TimeSpell"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
            setTextColor(0xFFe0e0e0.toInt())
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 48)
        }
        layout.addView(title)

        // Tema seçimi
        val themeLabel = TextView(this).apply {
            text = "Tema"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setTextColor(0xFFa0a0a0.toInt())
            setPadding(0, 0, 0, 8)
        }
        layout.addView(themeLabel)

        val themeNames = WordClockThemes.getThemeNames()
        val themeSpinner = Spinner(this)
        val themeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, themeNames)
        themeSpinner.adapter = themeAdapter
        themeSpinner.setSelection(prefs.getInt("theme", 1))
        layout.addView(themeSpinner)

        // Dil seçimi
        val langLabel = TextView(this).apply {
            text = "\nDil"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setTextColor(0xFFa0a0a0.toInt())
            setPadding(0, 24, 0, 8)
        }
        layout.addView(langLabel)

        val langOptions = arrayOf("Türkçe", "English")
        val langSpinner = Spinner(this)
        langSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, langOptions)
        langSpinner.setSelection(if (prefs.getString("language", "tr") == "en") 1 else 0)
        layout.addView(langSpinner)

        // Kaydet ve uygula butonu
        val applyBtn = Button(this).apply {
            text = "Duvar Kağıdı Olarak Ayarla"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setPadding(32, 24, 32, 24)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.topMargin = 64
            layoutParams = lp
        }
        applyBtn.setOnClickListener {
            prefs.edit()
                .putInt("theme", themeSpinner.selectedItemPosition)
                .putString("language", if (langSpinner.selectedItemPosition == 1) "en" else "tr")
                .apply()

            val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
                putExtra(
                    WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    ComponentName(this@SettingsActivity, WordClockWallpaperService::class.java)
                )
            }
            startActivity(intent)
        }
        layout.addView(applyBtn)

        // Sadece kaydet
        val saveBtn = Button(this).apply {
            text = "Ayarları Kaydet"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.topMargin = 16
            layoutParams = lp
        }
        saveBtn.setOnClickListener {
            prefs.edit()
                .putInt("theme", themeSpinner.selectedItemPosition)
                .putString("language", if (langSpinner.selectedItemPosition == 1) "en" else "tr")
                .apply()
            finish()
        }
        layout.addView(saveBtn)

        setContentView(layout)
    }
}
