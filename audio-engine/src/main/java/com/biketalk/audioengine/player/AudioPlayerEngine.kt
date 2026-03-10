package com.biketalk.audioengine.player

import com.biketalk.audioengine.AudioFrame

interface AudioPlayerEngine {
    fun play(frame: AudioFrame)
    fun start()
    fun stop()
}
