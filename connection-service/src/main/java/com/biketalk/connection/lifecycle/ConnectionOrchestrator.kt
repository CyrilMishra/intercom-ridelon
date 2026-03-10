package com.biketalk.connection.lifecycle

interface ConnectionOrchestrator {
    suspend fun connect()
    suspend fun disconnect()
}
