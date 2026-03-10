package com.biketalk.audio.player

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackAudioPlayer @Inject constructor() : AudioPlayer {
    private val track: AudioTrack by lazy {
        AudioTrack.Builder()
            .setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION).build())
            .setAudioFormat(AudioFormat.Builder().setSampleRate(48_000).setEncoding(AudioFormat.ENCODING_PCM_16BIT).setChannelMask(AudioFormat.CHANNEL_OUT_MONO).build())
            .setBufferSizeInBytes(48_000)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build().apply { play() }
    }

    override fun playPcm(frame: ShortArray) {
        track.write(frame, 0, frame.size, AudioTrack.WRITE_NON_BLOCKING)
    }

    override fun stop() = track.stop()
}
