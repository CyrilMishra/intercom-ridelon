package com.biketalk.networking.peerconnection

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WifiDirectSocketTransport @Inject constructor() {
    suspend fun open(peerId: String): Boolean = peerId.isNotBlank()
    suspend fun send(packet: ByteArray) { /* UDP socket fallback */ }
    suspend fun close() {}
}
