# BikeTalk

Multi-module Android intercom prototype for rider/pillion communication with shared music.

## Modules
- `app`: Compose UI, navigation, foreground service bootstrap
- `audio-engine`: low-latency capture/playback/mixing and OPUS codec abstraction
- `networking-engine`: WebRTC-first + WiFi Direct fallback transport abstraction
- `bluetooth-manager`: headset detection and SCO/A2DP routing
- `music-sync`: host/player control + timestamp drift correction
- `voice-processing`: VAD + echo/noise processing contracts
- `connection-service`: orchestration and auto-reconnect monitor

## Notes
- Configured for **minSdk 26**, **target/compile 35**, Kotlin + Hilt + Coroutines + Compose.
- This baseline prioritizes architecture and integration points; replace placeholder codec/ signaling logic with production WebRTC media tracks and OPUS JNI bindings for deployment.
