package com.moengage.test.newsapp.core.di

import com.moengage.test.core.network.service.NetworkManager
import com.moengage.test.newsapp.homescreen.data.datasource.NewsApiDataSource
import com.moengage.test.newsapp.homescreen.data.datasource.NewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsDataSource(networkManager: NetworkManager): NewsDataSource {
        return NewsApiDataSource(networkManager)
    }
}