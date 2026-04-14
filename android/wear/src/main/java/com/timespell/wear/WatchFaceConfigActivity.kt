package com.timespell.wear

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.wear.watchface.editor.EditorSession
import androidx.wear.watchface.style.UserStyleSetting
import kotlinx.coroutines.launch

class WatchFaceConfigActivity : ComponentActivity() {

    private lateinit var editorSession: EditorSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            editorSession = EditorSession.createOnWatchEditorSession(this@WatchFaceConfigActivity)
            buildUI()
        }
    }

    private fun buildUI() {
        val scrollView = ScrollView(this).apply {
            setBackgroundColor(Color.parseColor("#0c0c1e"))
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 32, 24, 32)
        }

        val title = TextView(this).apply {
            text = "Theme"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }
        layout.addView(title)

        val schema = editorSession.userStyleSchema
        val colorSetting = schema.rootUserStyleSettings.find {
            it.id == WordClockWatchFaceService.COLOR_STYLE_ID
        } as? UserStyleSetting.ListUserStyleSetting ?: return

        val currentStyle = editorSession.userStyle.value
        val currentOption = currentStyle[colorSetting] as? UserStyleSetting.ListUserStyleSetting.ListOption

        val themes = WordClockWatchFaceService.THEMES
        colorSetting.options.forEachIndexed { index, option ->
            val listOption = option as UserStyleSetting.ListUserStyleSetting.ListOption
            val theme = if (index < themes.size) themes[index] else return@forEachIndexed
            val accentColor = Color.parseColor(theme.onColor)
            val bgColor = Color.parseColor(theme.bgColor)
            val isSelected = listOption.id == currentOption?.id

            val btn = TextView(this).apply {
                text = theme.name
                textSize = 18f
                setTextColor(if (isSelected) Color.BLACK else accentColor)
                setBackgroundColor(if (isSelected) accentColor else bgColor)
                gravity = Gravity.CENTER
                setPadding(32, 20, 32, 20)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = 8 }
                layoutParams = params

                setOnClickListener {
                    val newStyle = editorSession.userStyle.value.toMutableUserStyle()
                    newStyle[colorSetting] = listOption
                    editorSession.userStyle.value = newStyle.toUserStyle()
                    editorSession.close()
                    finish()
                }
            }
            layout.addView(btn)
        }

        scrollView.addView(layout)
        setContentView(scrollView)
    }
}
