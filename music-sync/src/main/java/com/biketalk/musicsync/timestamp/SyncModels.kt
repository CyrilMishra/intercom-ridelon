package com.biketalk.musicsync.timestamp

data class SyncPacket(
    val playbackPositionMs: Long,
    val frameIndex: Long,
    val sentAtElapsedRealtimeMs: Long
)

data class DriftCorrection(val driftMs: Long, val withinTolerance: Boolean)
