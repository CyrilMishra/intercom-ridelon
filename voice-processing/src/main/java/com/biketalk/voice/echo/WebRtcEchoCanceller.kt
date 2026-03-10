package com.biketalk.voice.echo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebRtcEchoCanceller @Inject constructor() : EchoCanceller {
    override fun process(input: ShortArray): ShortArray = input // hook for platform AEC/NS
}
