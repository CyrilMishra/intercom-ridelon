package com.biketalk.voice.noise

import com.biketalk.audioengine.AudioFrame

interface NoiseSuppressor {
    fun process(frame: AudioFrame): AudioFrame
}

class WebRtcNoiseSuppressor : NoiseSuppressor {
    override fun process(frame: AudioFrame): AudioFrame = frame
}
