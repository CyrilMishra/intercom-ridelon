package com.biketalk.musicsync.timestamp

data class SyncPacket(
    val playbackPositionMs: Long,
    val frameIndex: Long,
    val driftCorrectionMs: Long
)
