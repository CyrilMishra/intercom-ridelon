package com.biketalk.networking

enum class LinkType { WEB_RTC, WIFI_DIRECT }

data class PeerDevice(val id: String, val displayName: String, val rssi: Int = -50)

data class ConnectionState(
    val connected: Boolean,
    val linkType: LinkType? = null,
    val latencyMs: Int = 0,
    val strength: Int = 0
)
