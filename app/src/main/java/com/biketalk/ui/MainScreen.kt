package com.biketalk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biketalk.viewmodel.IntercomViewModel

@Composable
fun MainScreen(viewModel: IntercomViewModel) {
    val state by viewModel.ui.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("BikeTalk", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = viewModel::connect, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.connected) "Connected" else "Connect")
        }
        Button(onClick = viewModel::toggleRole, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.isRider) "Role: Rider" else "Role: Pillion")
        }
        Button(onClick = viewModel::toggleMusic, modifier = Modifier.fillMaxWidth()) {
            Text(if (state.musicPlaying) "Pause Music" else "Play Music")
        }
        Text("Volume: ${(state.volume * 100).toInt()}%")
        Slider(value = state.volume, onValueChange = viewModel::setVolume)
        Text("Latency: ${state.latencyMs} ms")
        Text("Signal Strength: ${state.signalStrength}/4")
    }
}
