package com.biketalk.audio.mixer

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class PriorityAudioMixer @Inject constructor() : AudioMixer {
    override fun mix(voice: ShortArray, music: ShortArray?, speechDetected: Boolean): ShortArray {
        val duck = if (speechDetected) 0.2f else 0.7f
        return ShortArray(voice.size) { i ->
            val musicSample = (music?.getOrNull(i) ?: 0).toFloat() * duck
            (voice[i] + musicSample).roundToInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
        }
    }
}
