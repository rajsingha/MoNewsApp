package com.moengage.test.newsapp.core.di

import com.moengage.test.core.network.service.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module responsible for providing application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the [NetworkManager] for handling network operations.
     * @return Singleton instance of [NetworkManager]
     */
    @Singleton
    @Provides
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager
    }
}