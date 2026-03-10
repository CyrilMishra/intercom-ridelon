package com.biketalk.bluetooth.routing

interface BluetoothRouter {
    fun routeVoiceToSco(enable: Boolean)
    fun routeMusicToA2dp(enable: Boolean)
}
