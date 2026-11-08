package org.ajaypascua.accessible

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 28, 28, 28)
        }
        layout.addView(TextView(this).apply {
            text = "Accessible\n\nAn experimental Android screen reader. Enable the service in Android Accessibility settings to test speech."
            textSize = 20f
            gravity = Gravity.CENTER
        })
        layout.addView(Button(this).apply {
            text = "Open Accessibility settings"
            setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) }
        })
        layout.addView(Button(this).apply {
            text = "About testing"
            setOnClickListener {
                android.app.AlertDialog.Builder(this@MainActivity)
                    .setTitle("Testing version")
                    .setMessage("This early build reads accessibility-focused text. It is not yet a replacement for TalkBack. Keep your existing screen reader available while testing.")
                    .setPositiveButton("OK", null).show()
            }
        })
        setContentView(layout)
    }
}
