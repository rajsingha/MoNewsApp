package com.moengage.test.newsapp.homescreen.domain.repository

import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for accessing news articles.
 */
interface NewsRepo {

    /**
     * Retrieves news articles.
     * @return A flow representing the network response containing news articles.
     */
    suspend fun getNewsArticles(): Flow<NetworkResponse<NewsArticleResponse?>>
}
