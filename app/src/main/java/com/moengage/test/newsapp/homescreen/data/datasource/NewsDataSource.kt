package com.moengage.test.newsapp.homescreen.data.datasource

import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interface for defining data sources that provide news data.
 */
interface NewsDataSource {

    /**
     * Fetches news data.
     * @return A flow representing the network response containing news articles.
     */
    suspend fun getNewsData(): Flow<NetworkResponse<NewsArticleResponse?>>
}