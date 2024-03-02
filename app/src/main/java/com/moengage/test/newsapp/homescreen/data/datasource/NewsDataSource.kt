package com.moengage.test.newsapp.homescreen.data.datasource

import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.newsapp.homescreen.data.model.NewsArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun getNewsData(): Flow<NetworkResponse<NewsArticleResponse>>
}
