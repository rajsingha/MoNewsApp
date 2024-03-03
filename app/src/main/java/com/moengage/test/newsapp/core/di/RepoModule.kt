package com.moengage.test.newsapp.core.di

import com.moengage.test.newsapp.homescreen.data.repository.NewsRepoImpl
import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module responsible for providing repository dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    /**
     * Provides a singleton instance of [NewsRepo] to interact with news data.
     * @param repo The implementation of [NewsRepo], [NewsRepoImpl], to be provided as a singleton.
     * @return Singleton instance of [NewsRepo].
     */
    @Singleton
    @Binds
    abstract fun provideNewsRepo(repo: NewsRepoImpl): NewsRepo
}
