package com.biketalk.musicsync.timestamp

class ClockSynchronizer(private val maxDriftMs: Long = 20) {
    fun calculate(hostPositionMs: Long, localPositionMs: Long): DriftCorrection {
        val drift = hostPositionMs - localPositionMs
        return DriftCorrection(drift, kotlin.math.abs(drift) <= maxDriftMs)
    }
}
