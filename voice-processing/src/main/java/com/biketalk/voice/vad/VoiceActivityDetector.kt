package com.biketalk.voice.vad

interface VoiceActivityDetector {
    fun isSpeech(frame: ShortArray): Boolean
}
