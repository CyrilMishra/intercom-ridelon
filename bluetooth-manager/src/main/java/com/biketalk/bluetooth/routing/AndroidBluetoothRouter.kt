package com.biketalk.bluetooth.routing

import android.content.Context
import android.media.AudioManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidBluetoothRouter @Inject constructor(@ApplicationContext context: Context) : BluetoothRouter {
    private val audioManager = context.getSystemService(AudioManager::class.java)

    override fun routeVoiceToSco(enable: Boolean) {
        if (enable) {
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.startBluetoothSco()
            audioManager.isBluetoothScoOn = true
        } else {
            audioManager.stopBluetoothSco()
            audioManager.isBluetoothScoOn = false
        }
    }

    override fun routeMusicToA2dp(enable: Boolean) {
        audioManager.isSpeakerphoneOn = !enable
    }
}
