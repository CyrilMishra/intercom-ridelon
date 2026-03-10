package com.biketalk.musicsync.timestamp

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class TimestampMusicSynchronizer @Inject constructor() : MusicSynchronizer {
    override fun calculateDrift(localMs: Long, remoteMs: Long): Long = remoteMs - localMs
    override fun shouldCorrect(driftMs: Long): Boolean = abs(driftMs) > 20
}
