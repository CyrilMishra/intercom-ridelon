package com.biketalk.musicsync.client

import com.biketalk.musicsync.timestamp.MusicSynchronizer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientMusicReceiver @Inject constructor(
    private val synchronizer: MusicSynchronizer
) {
    fun adjustBuffer(localMs: Long, remoteMs: Long): Int {
        val drift = synchronizer.calculateDrift(localMs, remoteMs)
        return if (synchronizer.shouldCorrect(drift)) drift.toInt() else 0
    }
}
