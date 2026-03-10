package com.biketalk.audio.codec

import java.nio.ByteBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceholderOpusCodec @Inject constructor() : OpusCodec {
    override fun encode(pcm: ShortArray): ByteArray {
        val buffer = ByteBuffer.allocate(pcm.size * 2)
        pcm.forEach(buffer::putShort)
        return buffer.array()
    }

    override fun decode(payload: ByteArray): ShortArray {
        val buffer = ByteBuffer.wrap(payload)
        return ShortArray(payload.size / 2) { buffer.short }
    }
}
