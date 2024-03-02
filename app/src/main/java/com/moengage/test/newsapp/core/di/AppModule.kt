package com.moengage.test.newsapp.core.di

import com.moengage.test.core.network.service.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager
    }
}