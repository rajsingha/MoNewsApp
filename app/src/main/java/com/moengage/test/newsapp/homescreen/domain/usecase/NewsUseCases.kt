package com.moengage.test.newsapp.homescreen.domain.usecase

/**
 * Data class representing a collection of use cases.
 * @property getNewsArticleUseCase The use case for retrieving news articles.
 */
data class NewsUseCases(
    val getNewsArticleUseCase: GetNewsArticleUseCase,
)
