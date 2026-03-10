package com.biketalk.voice.vad

import com.biketalk.audioengine.AudioFrame
import kotlin.math.abs

interface VoiceActivityDetector {
    fun isSpeech(frame: AudioFrame): Boolean
}

class EnergyThresholdVad(private val threshold: Int = 1200) : VoiceActivityDetector {
    override fun isSpeech(frame: AudioFrame): Boolean {
        if (frame.pcm.isEmpty()) return false
        val avgEnergy = frame.pcm.map { abs(it.toInt()) }.average()
        return avgEnergy > threshold
    }
}
