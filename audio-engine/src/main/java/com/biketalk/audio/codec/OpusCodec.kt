package com.biketalk.audio.codec

interface OpusCodec {
    fun encode(pcm: ShortArray): ByteArray
    fun decode(payload: ByteArray): ShortArray
}
