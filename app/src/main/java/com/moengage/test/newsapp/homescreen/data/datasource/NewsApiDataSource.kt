package com.moengage.test.newsapp.homescreen.data.datasource

import com.moengage.test.core.network.service.NetworkManager
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import javax.inject.Inject


class NewsApiDataSource @Inject constructor(
    private val networkManager: NetworkManager
) : NewsDataSource {
    override suspend fun getNewsData() = networkManager.get<NewsArticleResponse>(NEW_ARTICLE_URL)

    companion object {
        private const val NEW_ARTICLE_URL =
            "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    }
}