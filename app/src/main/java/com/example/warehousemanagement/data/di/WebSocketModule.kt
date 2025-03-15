package com.example.warehousemanagement.data.di

import com.example.warehousemanagement.data.util.WebSocketManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideWebSocketManager(): WebSocketManager =
        WebSocketManager(baseUrl = "ws://192.168.0.143:8081/warehouse")
}
