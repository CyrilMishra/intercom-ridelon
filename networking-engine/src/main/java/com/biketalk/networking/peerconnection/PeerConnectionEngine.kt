package com.biketalk.networking.peerconnection

import com.biketalk.audioengine.EncodedAudioFrame
import com.biketalk.networking.ConnectionState
import com.biketalk.networking.PeerDevice
import kotlinx.coroutines.flow.Flow

interface PeerConnectionEngine {
    val state: Flow<ConnectionState>
    val incomingVoice: Flow<EncodedAudioFrame>
    val incomingMusic: Flow<EncodedAudioFrame>

    suspend fun discoverPeers(): List<PeerDevice>
    suspend fun connect(peer: PeerDevice)
    suspend fun sendVoice(frame: EncodedAudioFrame)
    suspend fun sendMusic(frame: EncodedAudioFrame)
    suspend fun disconnect()
}
