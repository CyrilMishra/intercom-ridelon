package com.biketalk.voice.di

import com.biketalk.voice.echo.EchoCanceller
import com.biketalk.voice.echo.WebRtcEchoCanceller
import com.biketalk.voice.noise.NoiseSuppressor
import com.biketalk.voice.noise.WebRtcNoiseSuppressor
import com.biketalk.voice.vad.HybridVad
import com.biketalk.voice.vad.VoiceActivityDetector
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VoiceProcessingModule {
    @Binds @Singleton abstract fun bindVad(impl: HybridVad): VoiceActivityDetector
    @Binds @Singleton abstract fun bindEcho(impl: WebRtcEchoCanceller): EchoCanceller
    @Binds @Singleton abstract fun bindNoise(impl: WebRtcNoiseSuppressor): NoiseSuppressor
}
