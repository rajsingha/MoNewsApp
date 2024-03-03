package com.moengage.test.newsapp.homescreen.domain.usecase

import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Use case responsible for retrieving news articles.
 * @param newsRepo The repository for accessing news articles.
 */
@ViewModelScoped
class GetNewsArticleUseCase @Inject constructor(
    private val newsRepo: NewsRepo,
) {
    /**
     * Invokes the use case to retrieve news articles information asynchronously.
     * @return A flow representing the network response containing news articles.
     */
    suspend operator fun invoke() = newsRepo.getNewsArticles()
}