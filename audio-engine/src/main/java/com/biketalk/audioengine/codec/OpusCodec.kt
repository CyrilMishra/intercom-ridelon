package com.biketalk.audioengine.codec

import com.biketalk.audioengine.AudioFrame
import com.biketalk.audioengine.EncodedAudioFrame

interface OpusCodec {
    fun encode(frame: AudioFrame): EncodedAudioFrame
    fun decode(frame: EncodedAudioFrame): AudioFrame
}

/**
 * Placeholder OPUS codec interface. Replace with JNI/libopus-backed implementation for production.
 */
class StubOpusCodec : OpusCodec {
    override fun encode(frame: AudioFrame): EncodedAudioFrame {
        val bytes = ByteArray(frame.pcm.size * 2)
        frame.pcm.forEachIndexed { i, s ->
            bytes[i * 2] = (s.toInt() and 0xFF).toByte()
            bytes[i * 2 + 1] = ((s.toInt() shr 8) and 0xFF).toByte()
        }
        return EncodedAudioFrame(bytes, frame.timestampUs, frame.timestampUs)
    }

    override fun decode(frame: EncodedAudioFrame): AudioFrame {
        val shorts = ShortArray(frame.payload.size / 2)
        for (i in shorts.indices) {
            val lo = frame.payload[i * 2].toInt() and 0xFF
            val hi = frame.payload[i * 2 + 1].toInt() shl 8
            shorts[i] = (hi or lo).toShort()
        }
        return AudioFrame(shorts, frame.timestampUs)
    }
}
