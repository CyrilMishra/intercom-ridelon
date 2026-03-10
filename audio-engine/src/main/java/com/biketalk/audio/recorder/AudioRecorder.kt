package com.biketalk.audio.recorder

interface AudioRecorder {
    suspend fun start(onFrame: (ShortArray, Long) -> Unit)
    fun stop()
}
