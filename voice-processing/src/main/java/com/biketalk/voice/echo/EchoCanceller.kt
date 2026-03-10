package com.biketalk.voice.echo

import com.biketalk.audioengine.AudioFrame

interface EchoCanceller {
    fun process(frame: AudioFrame): AudioFrame
}

class WebRtcEchoCanceller : EchoCanceller {
    override fun process(frame: AudioFrame): AudioFrame = frame
}
