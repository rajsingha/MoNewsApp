package com.moengage.test.newsapp.core.di

import com.moengage.test.newsapp.homescreen.domain.usecase.GetNewsArticleUseCase
import com.moengage.test.newsapp.homescreen.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    /**
     * Provides view model scoped instance of [NewsUseCases]
     */
    @Provides
    @ViewModelScoped
    fun provideNewsArticleUseCases(
        getNewsArticleUseCase: GetNewsArticleUseCase
    ) = NewsUseCases(
        getNewsArticleUseCase
    )
}