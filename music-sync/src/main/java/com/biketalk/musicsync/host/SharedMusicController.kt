package com.biketalk.musicsync.host

interface SharedMusicController {
    suspend fun play()
    suspend fun pause()
    fun setVolume(volume: Float)
}
