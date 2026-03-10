package com.biketalk.connection.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class ForegroundNotifier(private val context: Context) {
    private val channelId = "biketalk_intercom"

    fun buildNotification(content: String): Notification {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(
            NotificationChannel(channelId, "BikeTalk Intercom", NotificationManager.IMPORTANCE_LOW)
        )
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle("BikeTalk running")
            .setContentText(content)
            .setSmallIcon(android.R.drawable.stat_sys_headset)
            .setOngoing(true)
            .build()
    }
}
