package com.biketalk.connection.lifecycle

import com.biketalk.audio.codec.OpusCodec
import com.biketalk.audio.mixer.AudioMixer
import com.biketalk.audio.player.AudioPlayer
import com.biketalk.audio.recorder.AudioRecorder
import com.biketalk.bluetooth.routing.BluetoothRouter
import com.biketalk.networking.webrtc.IntercomTransport
import com.biketalk.voice.echo.EchoCanceller
import com.biketalk.voice.noise.NoiseSuppressor
import com.biketalk.voice.vad.VoiceActivityDetector
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Singleton
class DefaultConnectionOrchestrator @Inject constructor(
    private val recorder: AudioRecorder,
    private val player: AudioPlayer,
    private val codec: OpusCodec,
    private val transport: IntercomTransport,
    private val echo: EchoCanceller,
    private val noise: NoiseSuppressor,
    private val vad: VoiceActivityDetector,
    private val mixer: AudioMixer,
    private val bluetoothRouter: BluetoothRouter
) : ConnectionOrchestrator {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override suspend fun connect() {
        bluetoothRouter.routeVoiceToSco(true)
        bluetoothRouter.routeMusicToA2dp(true)
        val peer = transport.discoverPeer() ?: return
        transport.connect(peer)
        recorder.start { frame, _ ->
            val cleaned = noise.process(echo.process(frame))
            val speech = vad.isSpeech(cleaned)
            val payload = codec.encode(cleaned)
            scope.launch { transport.send(payload) }
            player.playPcm(mixer.mix(cleaned, null, speech))
        }
    }

    override suspend fun disconnect() {
        recorder.stop()
        player.stop()
        transport.disconnect()
        bluetoothRouter.routeVoiceToSco(false)
    }
}
