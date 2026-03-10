package com.biketalk.audioengine.mixer

import com.biketalk.audioengine.AudioFrame
import kotlin.math.max
import kotlin.math.min

interface AudioMixer {
    fun mix(voice: AudioFrame, music: AudioFrame?, voiceActive: Boolean): AudioFrame
}

class PriorityAudioMixer : AudioMixer {
    override fun mix(voice: AudioFrame, music: AudioFrame?, voiceActive: Boolean): AudioFrame {
        if (music == null) return voice
        val duck = if (voiceActive) 0.25f else 1f
        val out = ShortArray(voice.pcm.size)
        for (i in out.indices) {
            val musicSample = if (i < music.pcm.size) (music.pcm[i] * duck).toInt() else 0
            val mix = voice.pcm[i].toInt() + musicSample
            out[i] = min(Short.MAX_VALUE.toInt(), max(Short.MIN_VALUE.toInt(), mix)).toShort()
        }
        return voice.copy(pcm = out)
    }
}
