package com.biketalk.bluetooth.di

import com.biketalk.bluetooth.headset.HeadsetDetector
import com.biketalk.bluetooth.headset.SystemHeadsetDetector
import com.biketalk.bluetooth.routing.AndroidBluetoothRouter
import com.biketalk.bluetooth.routing.BluetoothRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BluetoothModule {
    @Binds @Singleton abstract fun bindHeadsetDetector(impl: SystemHeadsetDetector): HeadsetDetector
    @Binds @Singleton abstract fun bindBluetoothRouter(impl: AndroidBluetoothRouter): BluetoothRouter
}
