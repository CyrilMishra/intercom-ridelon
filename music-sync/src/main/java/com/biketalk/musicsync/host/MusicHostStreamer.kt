package com.biketalk.musicsync.host

import androidx.media3.exoplayer.ExoPlayer
import com.biketalk.musicsync.timestamp.SyncPacket

interface MusicHostStreamer {
    fun currentSyncPacket(frameIndex: Long): SyncPacket
}

class ExoMusicHostStreamer(private val player: ExoPlayer) : MusicHostStreamer {
    override fun currentSyncPacket(frameIndex: Long): SyncPacket = SyncPacket(
        playbackPositionMs = player.currentPosition,
        frameIndex = frameIndex,
        sentAtElapsedRealtimeMs = System.currentTimeMillis()
    )
}
