package com.biketalk.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biketalk.connection.lifecycle.ConnectionOrchestrator
import com.biketalk.musicsync.host.SharedMusicController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val connected: Boolean = false,
    val isRider: Boolean = true,
    val musicPlaying: Boolean = false,
    val volume: Float = 0.8f,
    val latencyMs: Int = 0,
    val signalStrength: String = "Unknown"
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val orchestrator: ConnectionOrchestrator,
    private val musicController: SharedMusicController
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun toggleConnection() = viewModelScope.launch {
        val currentlyConnected = _uiState.value.connected
        if (currentlyConnected) orchestrator.disconnect() else orchestrator.connect()
        _uiState.value = _uiState.value.copy(connected = !currentlyConnected)
    }

    fun setRiderMode(enabled: Boolean) { _uiState.value = _uiState.value.copy(isRider = enabled) }

    fun toggleMusic() = viewModelScope.launch {
        val playing = _uiState.value.musicPlaying
        if (playing) musicController.pause() else musicController.play()
        _uiState.value = _uiState.value.copy(musicPlaying = !playing)
    }

    fun setVolume(volume: Float) {
        musicController.setVolume(volume)
        _uiState.value = _uiState.value.copy(volume = volume)
    }
}
