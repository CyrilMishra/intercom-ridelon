package com.biketalk.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import com.biketalk.connection.foreground.ForegroundNotifier
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntercomForegroundService : Service() {
    @Inject lateinit var foregroundNotifier: ForegroundNotifier
    private var wakeLock: PowerManager.WakeLock? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(101, foregroundNotifier.buildNotification("Intercom active"))
        val manager = getSystemService(POWER_SERVICE) as PowerManager
        wakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "BikeTalk::Intercom").apply {
            acquire(10 * 60 * 1000L)
        }
    }

    override fun onDestroy() {
        wakeLock?.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
