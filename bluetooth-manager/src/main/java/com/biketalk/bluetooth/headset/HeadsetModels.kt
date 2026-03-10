package com.biketalk.bluetooth.headset

data class HeadsetState(
    val connected: Boolean,
    val name: String? = null,
    val scoActive: Boolean = false,
    val a2dpActive: Boolean = false
)
