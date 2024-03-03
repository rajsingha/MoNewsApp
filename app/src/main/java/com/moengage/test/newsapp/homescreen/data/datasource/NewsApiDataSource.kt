package com.moengage.test.newsapp.homescreen.data.datasource

import com.moengage.test.core.network.service.NetworkManager
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import javax.inject.Inject


/**
 * Implementation of [NewsDataSource] that fetches news data from a remote API.
 * @param networkManager The network manager used for network operations.
 */
class NewsApiDataSource @Inject constructor(
    private val networkManager: NetworkManager
) : NewsDataSource {

    /**
     * Fetches news data from a remote API.
     * @return A suspend function returning [NewsArticleResponse] obtained from the API.
     */
    override suspend fun getNewsData() = networkManager.get<NewsArticleResponse>(NEW_ARTICLE_URL)

    companion object {
        // URL for fetching news data
        private const val NEW_ARTICLE_URL =
            "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"
    }
}