package com.biketalk.voice.vad

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class HybridVad @Inject constructor() : VoiceActivityDetector {
    override fun isSpeech(frame: ShortArray): Boolean {
        val energy = frame.sumOf { abs(it.toInt()) } / frame.size
        return energy > 1200 // basic threshold tuned for helmet wind noise floor.
    }
}
