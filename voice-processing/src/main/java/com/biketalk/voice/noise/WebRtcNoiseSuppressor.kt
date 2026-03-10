package com.biketalk.voice.noise

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebRtcNoiseSuppressor @Inject constructor() : NoiseSuppressor {
    override fun process(input: ShortArray): ShortArray = input
}
