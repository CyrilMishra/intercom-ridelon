package com.biketalk.networking.signaling

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSignalingClient @Inject constructor() : SignalingClient {
    override suspend fun discoverNearbyPeer(): String? = "nearby-bike-partner"
    override suspend fun openWebRtc(peerId: String): Boolean = peerId.isNotBlank()
    override suspend fun send(packet: ByteArray): Boolean = packet.isNotEmpty()
    override suspend fun close() = Unit
}
