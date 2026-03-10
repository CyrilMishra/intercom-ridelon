package com.biketalk.networking.signaling

import com.biketalk.audioengine.EncodedAudioFrame
import com.biketalk.networking.ConnectionState
import com.biketalk.networking.LinkType
import com.biketalk.networking.PeerDevice
import com.biketalk.networking.peerconnection.PeerConnectionEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow

class WifiDirectFallbackEngine : PeerConnectionEngine {
    private val _state = MutableStateFlow(ConnectionState(connected = false))
    override val state: Flow<ConnectionState> = _state
    override val incomingVoice: Flow<EncodedAudioFrame> = emptyFlow()
    override val incomingMusic: Flow<EncodedAudioFrame> = emptyFlow()

    override suspend fun discoverPeers(): List<PeerDevice> = emptyList()

    override suspend fun connect(peer: PeerDevice) {
        _state.value = ConnectionState(true, LinkType.WIFI_DIRECT, latencyMs = 48, strength = 3)
    }

    override suspend fun sendVoice(frame: EncodedAudioFrame) = Unit
    override suspend fun sendMusic(frame: EncodedAudioFrame) = Unit

    override suspend fun disconnect() {
        _state.value = ConnectionState(false)
    }
}
