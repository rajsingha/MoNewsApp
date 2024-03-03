package com.moengage.test.newsapp.core.di

import com.moengage.test.newsapp.homescreen.domain.usecase.GetNewsArticleUseCase
import com.moengage.test.newsapp.homescreen.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Dagger module responsible for providing use case dependencies.
 */
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    /**
     * Provides a view model scoped instance of [NewsUseCases].
     * @param getNewsArticleUseCase The use case for retrieving news articles.
     * @return Instance of [NewsUseCases] scoped to the ViewModel.
     */
    @Provides
    @ViewModelScoped
    fun provideNewsArticleUseCases(
        getNewsArticleUseCase: GetNewsArticleUseCase
    ) = NewsUseCases(
        getNewsArticleUseCase
    )
}
