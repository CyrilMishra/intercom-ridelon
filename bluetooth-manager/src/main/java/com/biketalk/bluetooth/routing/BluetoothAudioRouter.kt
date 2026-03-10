package com.biketalk.bluetooth.routing

import android.content.Context
import android.media.AudioManager
import com.biketalk.bluetooth.headset.HeadsetMonitor
import com.biketalk.bluetooth.headset.HeadsetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AudioRouter {
    fun enableIntercomRouting()
    fun disableIntercomRouting()
}

class BluetoothAudioRouter(
    context: Context
) : AudioRouter, HeadsetMonitor {
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val _headsetState = MutableStateFlow(HeadsetState(false))
    override val headsetState: StateFlow<HeadsetState> = _headsetState

    override fun enableIntercomRouting() {
        audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        audioManager.isBluetoothScoOn = true
        audioManager.startBluetoothSco()
        _headsetState.value = HeadsetState(connected = true, scoActive = true, a2dpActive = true)
    }

    override fun disableIntercomRouting() {
        audioManager.stopBluetoothSco()
        audioManager.isBluetoothScoOn = false
        audioManager.mode = AudioManager.MODE_NORMAL
        _headsetState.value = _headsetState.value.copy(scoActive = false)
    }
}
