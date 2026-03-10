package com.biketalk.di

import android.content.Context
import com.biketalk.audioengine.codec.OpusCodec
import com.biketalk.audioengine.codec.StubOpusCodec
import com.biketalk.audioengine.mixer.AudioMixer
import com.biketalk.audioengine.mixer.PriorityAudioMixer
import com.biketalk.audioengine.player.AndroidAudioTrackPlayer
import com.biketalk.audioengine.player.AudioPlayerEngine
import com.biketalk.audioengine.recorder.AndroidAudioRecorderEngine
import com.biketalk.audioengine.recorder.AudioRecorderEngine
import com.biketalk.bluetooth.routing.AudioRouter
import com.biketalk.bluetooth.routing.BluetoothAudioRouter
import com.biketalk.connection.foreground.ForegroundNotifier
import com.biketalk.networking.peerconnection.PeerConnectionEngine
import com.biketalk.networking.webrtc.WebRtcPeerConnectionEngine
import com.biketalk.voice.echo.EchoCanceller
import com.biketalk.voice.echo.WebRtcEchoCanceller
import com.biketalk.voice.noise.NoiseSuppressor
import com.biketalk.voice.noise.WebRtcNoiseSuppressor
import com.biketalk.voice.vad.EnergyThresholdVad
import com.biketalk.voice.vad.VoiceActivityDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton fun provideRecorder(): AudioRecorderEngine = AndroidAudioRecorderEngine()
    @Provides @Singleton fun providePlayer(): AudioPlayerEngine = AndroidAudioTrackPlayer()
    @Provides @Singleton fun provideMixer(): AudioMixer = PriorityAudioMixer()
    @Provides @Singleton fun provideCodec(): OpusCodec = StubOpusCodec()
    @Provides @Singleton fun providePeerEngine(): PeerConnectionEngine = WebRtcPeerConnectionEngine()
    @Provides @Singleton fun provideVad(): VoiceActivityDetector = EnergyThresholdVad()
    @Provides @Singleton fun provideEcho(): EchoCanceller = WebRtcEchoCanceller()
    @Provides @Singleton fun provideNoise(): NoiseSuppressor = WebRtcNoiseSuppressor()
    @Provides @Singleton fun provideNotifier(@ApplicationContext ctx: Context) = ForegroundNotifier(ctx)
    @Provides @Singleton fun provideAudioRouter(@ApplicationContext ctx: Context): AudioRouter = BluetoothAudioRouter(ctx)
}
