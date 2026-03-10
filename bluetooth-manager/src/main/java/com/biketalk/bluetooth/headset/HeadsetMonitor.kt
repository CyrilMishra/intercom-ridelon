package com.biketalk.bluetooth.headset

import kotlinx.coroutines.flow.StateFlow

interface HeadsetMonitor {
    val headsetState: StateFlow<HeadsetState>
}
