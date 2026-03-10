package com.biketalk.networking.signaling

interface SignalingClient {
    suspend fun discoverNearbyPeer(): String?
    suspend fun openWebRtc(peerId: String): Boolean
    suspend fun send(packet: ByteArray): Boolean
    suspend fun close()
}
