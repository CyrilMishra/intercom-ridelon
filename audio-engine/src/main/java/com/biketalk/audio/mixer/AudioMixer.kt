package com.biketalk.audio.mixer

interface AudioMixer {
    fun mix(voice: ShortArray, music: ShortArray?, speechDetected: Boolean): ShortArray
}
