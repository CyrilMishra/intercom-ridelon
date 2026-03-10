package com.biketalk.networking.webrtc

import kotlinx.coroutines.flow.StateFlow

interface IntercomTransport {
    val connected: StateFlow<Boolean>
    suspend fun connect(peerId: String)
    suspend fun send(packet: ByteArray)
    suspend fun discoverPeer(): String?
    suspend fun disconnect()
}
