package com.biketalk.connection.di

import com.biketalk.connection.lifecycle.ConnectionOrchestrator
import com.biketalk.connection.lifecycle.DefaultConnectionOrchestrator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectionModule {
    @Binds @Singleton abstract fun bindOrchestrator(impl: DefaultConnectionOrchestrator): ConnectionOrchestrator
}
