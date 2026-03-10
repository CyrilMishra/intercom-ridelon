package com.biketalk.voice.noise

interface NoiseSuppressor {
    fun process(input: ShortArray): ShortArray
}
