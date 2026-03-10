package com.biketalk.musicsync.timestamp

interface MusicSynchronizer {
    fun calculateDrift(localMs: Long, remoteMs: Long): Long
    fun shouldCorrect(driftMs: Long): Boolean
}
