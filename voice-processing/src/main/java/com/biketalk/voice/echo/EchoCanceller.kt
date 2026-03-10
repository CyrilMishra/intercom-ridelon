package com.biketalk.voice.echo

interface EchoCanceller {
    fun process(input: ShortArray): ShortArray
}
