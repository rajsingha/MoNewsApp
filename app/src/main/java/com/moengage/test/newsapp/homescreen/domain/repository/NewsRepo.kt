package com.moengage.test.newsapp.homescreen.domain.repository

import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.newsapp.homescreen.data.model.NewsArticleResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    suspend fun getNewsArticles(): Flow<NetworkResponse<NewsArticleResponse?>>
}
