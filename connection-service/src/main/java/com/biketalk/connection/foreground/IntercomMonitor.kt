package com.biketalk.connection.foreground

import com.biketalk.connection.lifecycle.ConnectionOrchestrator
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay

@Singleton
class IntercomMonitor @Inject constructor(private val orchestrator: ConnectionOrchestrator) {
    suspend fun monitorAndReconnect() {
        // lightweight watchdog for periodic reconnect attempts.
        while (true) {
            delay(5_000)
            orchestrator.connect()
        }
    }
}
