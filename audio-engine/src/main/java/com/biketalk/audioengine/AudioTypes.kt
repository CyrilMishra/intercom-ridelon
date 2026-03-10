package com.biketalk.audioengine

data class AudioFrame(
    val pcm: ShortArray,
    val timestampUs: Long,
    val sampleRate: Int = 48_000,
    val channels: Int = 1
)

data class EncodedAudioFrame(
    val payload: ByteArray,
    val timestampUs: Long,
    val sequence: Long
)
