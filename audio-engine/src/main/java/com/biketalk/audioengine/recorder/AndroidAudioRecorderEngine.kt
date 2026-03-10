package com.biketalk.audioengine.recorder

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import com.biketalk.audioengine.AudioFrame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class AndroidAudioRecorderEngine : AudioRecorderEngine {
    private var audioRecord: AudioRecord? = null

    override fun start(): Flow<AudioFrame> = callbackFlow {
        val sampleRate = 48_000
        val frameSamples = sampleRate / 100 // 10ms
        val minBuffer = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val bufferSize = maxOf(minBuffer, frameSamples * 4)
        val recorder = AudioRecord(
            MediaRecorder.AudioSource.VOICE_COMMUNICATION,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )
        audioRecord = recorder
        recorder.startRecording()
        launch(Dispatchers.IO) {
            val frame = ShortArray(frameSamples)
            while (!isClosedForSend) {
                val read = recorder.read(frame, 0, frame.size)
                if (read > 0) {
                    trySend(AudioFrame(frame.copyOf(read), System.nanoTime() / 1000))
                }
            }
        }
        awaitClose {
            recorder.stop()
            recorder.release()
        }
    }

    override fun stop() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }
}
