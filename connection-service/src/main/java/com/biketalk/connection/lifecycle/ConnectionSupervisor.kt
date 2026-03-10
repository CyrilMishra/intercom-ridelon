package com.biketalk.connection.lifecycle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class ConnectionSupervisor {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}
