package com.biketalk.musicsync.di

import com.biketalk.musicsync.host.HostMusicStreamer
import com.biketalk.musicsync.host.SharedMusicController
import com.biketalk.musicsync.timestamp.MusicSynchronizer
import com.biketalk.musicsync.timestamp.TimestampMusicSynchronizer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MusicSyncModule {
    @Binds @Singleton abstract fun bindSync(impl: TimestampMusicSynchronizer): MusicSynchronizer
    @Binds @Singleton abstract fun bindMusicController(impl: HostMusicStreamer): SharedMusicController
}
