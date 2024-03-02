package com.moengage.test.newsapp.homescreen.domain.usecase

import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetNewsArticleUseCase @Inject constructor(
    /**
     * Repository for Salaried In Bank employment related data
     */
    private val newsRepo: NewsRepo,
) {

    /**
     * Invokes the use case to retrieve news articles information asynchronously.
     */
    suspend operator fun invoke() = newsRepo.getNewsArticles()
}