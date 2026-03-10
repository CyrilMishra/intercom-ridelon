package com.biketalk.networking.peerconnection

import com.biketalk.networking.signaling.SignalingClient
import com.biketalk.networking.webrtc.IntercomTransport
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Singleton
class WebRtcIntercomTransport @Inject constructor(
    private val signalingClient: SignalingClient,
    private val wifiFallback: WifiDirectSocketTransport
) : IntercomTransport {
    private val _connected = MutableStateFlow(false)
    override val connected: StateFlow<Boolean> = _connected

    override suspend fun connect(peerId: String) {
        // Full WebRTC handshake (SDP + ICE) would happen here.
        _connected.value = signalingClient.openWebRtc(peerId) || wifiFallback.open(peerId)
    }

    override suspend fun send(packet: ByteArray) {
        if (!_connected.value) return
        if (!signalingClient.send(packet)) wifiFallback.send(packet)
    }

    override suspend fun discoverPeer(): String? = signalingClient.discoverNearbyPeer()

    override suspend fun disconnect() {
        signalingClient.close()
        wifiFallback.close()
        _connected.value = false
    }
}
