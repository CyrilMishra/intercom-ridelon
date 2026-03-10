package com.biketalk.audio.recorder

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioRecordRecorder @Inject constructor() : AudioRecorder {
    private var job: Job? = null
    private val sampleRate = 48_000
    private val frameSize = sampleRate / 100 // 10ms

    override suspend fun start(onFrame: (ShortArray, Long) -> Unit) {
        val minBuffer = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
        val recorder = AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, minBuffer)
        recorder.startRecording()
        job = CoroutineScope(Dispatchers.Default).launch {
            val frame = ShortArray(frameSize)
            while (isActive) {
                recorder.read(frame, 0, frame.size)
                onFrame(frame.copyOf(), System.nanoTime())
            }
        }
    }

    override fun stop() { job?.cancel() }
}
