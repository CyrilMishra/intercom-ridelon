package com.biketalk.audioengine.recorder

import com.biketalk.audioengine.AudioFrame
import kotlinx.coroutines.flow.Flow

interface AudioRecorderEngine {
    fun start(): Flow<AudioFrame>
    fun stop()
}
