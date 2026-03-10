package com.biketalk.musicsync.host

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.biketalk.musicsync.timestamp.MusicSynchronizer
import com.biketalk.musicsync.timestamp.SyncPacket
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HostMusicStreamer @Inject constructor(
    @ApplicationContext context: Context,
    private val synchronizer: MusicSynchronizer
) : SharedMusicController {
    private val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.fromUri("asset:///sample.mp3"))
        prepare()
    }

    override suspend fun play() = player.play()
    override suspend fun pause() = player.pause()
    override fun setVolume(volume: Float) { player.volume = volume }

    fun syncPacket(frameIndex: Long): SyncPacket {
        val driftCorrection = if (synchronizer.shouldCorrect(0)) 1 else 0
        return SyncPacket(player.currentPosition, frameIndex, driftCorrection.toLong())
    }
}
