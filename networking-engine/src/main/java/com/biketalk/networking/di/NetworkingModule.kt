package com.biketalk.networking.di

import com.biketalk.networking.peerconnection.WebRtcIntercomTransport
import com.biketalk.networking.signaling.LocalSignalingClient
import com.biketalk.networking.signaling.SignalingClient
import com.biketalk.networking.webrtc.IntercomTransport
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkingModule {
    @Binds @Singleton abstract fun bindTransport(impl: WebRtcIntercomTransport): IntercomTransport
    @Binds @Singleton abstract fun bindSignaling(impl: LocalSignalingClient): SignalingClient
}
