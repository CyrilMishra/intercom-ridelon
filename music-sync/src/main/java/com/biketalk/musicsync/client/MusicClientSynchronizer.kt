package com.biketalk.musicsync.client

import com.biketalk.musicsync.timestamp.ClockSynchronizer
import com.biketalk.musicsync.timestamp.SyncPacket

interface MusicClientSynchronizer {
    fun apply(packet: SyncPacket, localPositionMs: Long): Long
}

class DynamicBufferMusicClientSynchronizer(
    private val clockSynchronizer: ClockSynchronizer = ClockSynchronizer()
) : MusicClientSynchronizer {
    override fun apply(packet: SyncPacket, localPositionMs: Long): Long {
        val correction = clockSynchronizer.calculate(packet.playbackPositionMs, localPositionMs)
        return if (correction.withinTolerance) localPositionMs else localPositionMs + correction.driftMs
    }
}
