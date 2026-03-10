package com.biketalk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biketalk.networking.ConnectionState
import com.biketalk.networking.PeerDevice
import com.biketalk.networking.peerconnection.PeerConnectionEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class IntercomViewModel @Inject constructor(
    private val peerConnectionEngine: PeerConnectionEngine
) : ViewModel() {
    data class UiState(
        val connected: Boolean = false,
        val isRider: Boolean = true,
        val musicPlaying: Boolean = false,
        val volume: Float = 0.8f,
        val latencyMs: Int = 0,
        val signalStrength: Int = 0
    )

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui.asStateFlow()

    init {
        viewModelScope.launch {
            peerConnectionEngine.state.collect(::applyConnectionState)
        }
    }

    fun connect() = viewModelScope.launch {
        val peers: List<PeerDevice> = peerConnectionEngine.discoverPeers()
        peers.firstOrNull()?.let { peerConnectionEngine.connect(it) }
    }

    fun toggleRole() = _ui.update { it.copy(isRider = !it.isRider) }
    fun toggleMusic() = _ui.update { it.copy(musicPlaying = !it.musicPlaying) }
    fun setVolume(value: Float) = _ui.update { it.copy(volume = value) }

    private fun applyConnectionState(state: ConnectionState) {
        _ui.update {
            it.copy(
                connected = state.connected,
                latencyMs = state.latencyMs,
                signalStrength = state.strength
            )
        }
    }
}
