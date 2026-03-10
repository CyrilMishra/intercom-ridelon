# BikeTalk

BikeTalk is a multi-module Android project for rider-pillion intercom, shared music, and Bluetooth headset routing.

## Modules
- `app`: Compose UI, ViewModel, navigation, DI, foreground service.
- `audio-engine`: 10 ms frame recorder/player, mixer, OPUS codec abstraction.
- `networking-engine`: WebRTC first with Wi-Fi Direct fallback abstractions.
- `bluetooth-manager`: Headset state + SCO/A2DP routing helpers.
- `music-sync`: host/client timestamp synchronization with drift correction.
- `voice-processing`: VAD + echo/noise processing interfaces.
- `connection-service`: service lifecycle and persistent notification helpers.

## Notes
This scaffold compiles and is intentionally structured for extending with production-grade WebRTC signaling, OPUS JNI codec, jitter buffers, and robust Android Bluetooth state management.
