package com.moengage.test.newsapp.core.di

import com.moengage.test.newsapp.homescreen.data.repository.NewsRepoImpl
import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun provideNewsRepo(repo: NewsRepoImpl): NewsRepo

}