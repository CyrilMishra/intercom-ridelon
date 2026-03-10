package com.biketalk.audio.player

interface AudioPlayer {
    fun playPcm(frame: ShortArray)
    fun stop()
}
