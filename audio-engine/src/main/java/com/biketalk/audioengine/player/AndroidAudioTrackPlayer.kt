package com.biketalk.audioengine.player

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import com.biketalk.audioengine.AudioFrame

class AndroidAudioTrackPlayer : AudioPlayerEngine {
    private val sampleRate = 48_000
    private val track: AudioTrack = AudioTrack(
        AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION).build(),
        AudioFormat.Builder()
            .setSampleRate(sampleRate)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .build(),
        AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        ),
        AudioTrack.MODE_STREAM,
        AudioManager.AUDIO_SESSION_ID_GENERATE
    )

    override fun play(frame: AudioFrame) {
        track.write(frame.pcm, 0, frame.pcm.size)
    }

    override fun start() = track.play()
    override fun stop() {
        track.stop()
        track.release()
    }
}
