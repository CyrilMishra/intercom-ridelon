package com.biketalk.audio.di

import com.biketalk.audio.codec.OpusCodec
import com.biketalk.audio.codec.PlaceholderOpusCodec
import com.biketalk.audio.mixer.AudioMixer
import com.biketalk.audio.mixer.PriorityAudioMixer
import com.biketalk.audio.player.AudioPlayer
import com.biketalk.audio.player.TrackAudioPlayer
import com.biketalk.audio.recorder.AudioRecordRecorder
import com.biketalk.audio.recorder.AudioRecorder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AudioEngineModule {
    @Binds @Singleton abstract fun bindRecorder(impl: AudioRecordRecorder): AudioRecorder
    @Binds @Singleton abstract fun bindPlayer(impl: TrackAudioPlayer): AudioPlayer
    @Binds @Singleton abstract fun bindMixer(impl: PriorityAudioMixer): AudioMixer
    @Binds @Singleton abstract fun bindCodec(impl: PlaceholderOpusCodec): OpusCodec
}
