package org.ajaypascua.accessible

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.speech.tts.TextToSpeech
import java.util.Locale

class AccessibleService : AccessibilityService(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var ready = false
    private var lastUtterance = ""

    override fun onServiceConnected() {
        super.onServiceConnected()
        tts = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.getDefault())
            ready = result != TextToSpeech.LANG_MISSING_DATA &&
                    result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!ready || event == null) return
        val parts = mutableListOf<String>()
        event.text?.forEach { value ->
            value?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.let(parts::add)
        }
        val description = event.contentDescription?.toString()?.trim()
        if (!description.isNullOrEmpty()) parts.add(description)
        val message = parts.distinct().joinToString(". ").take(700)
        if (message.isNotBlank() && message != lastUtterance) {
            lastUtterance = message
            tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, "accessible-event")
        }
    }

    override fun onInterrupt() {
        tts?.stop()
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        super.onDestroy()
    }
}
