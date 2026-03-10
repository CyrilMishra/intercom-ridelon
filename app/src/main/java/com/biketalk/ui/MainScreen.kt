package com.biketalk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biketalk.viewmodel.MainViewModel

@Composable
fun MainScreen(vm: MainViewModel) {
    val state by vm.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("BikeTalk", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = vm::toggleConnection, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.connected) "Disconnect" else "Connect")
        }
        Text("Mode: ${if (state.isRider) "Rider" else "Pillion"}")
        Switch(checked = state.isRider, onCheckedChange = vm::setRiderMode)
        Button(onClick = vm::toggleMusic, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.musicPlaying) "Pause Music" else "Play Music")
        }
        Text("Volume")
        Slider(value = state.volume, onValueChange = vm::setVolume)
        Text("Latency: ${state.latencyMs} ms")
        Text("Signal: ${state.signalStrength}")
    }
}
