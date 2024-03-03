package com.moengage.test.newsapp.homescreen.data.repository

import com.moengage.test.newsapp.homescreen.data.datasource.NewsDataSource
import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import javax.inject.Inject

/**
 * Implementation of [NewsRepo] responsible for fetching news articles.
 * @property newsDataSource The data source for fetching news articles.
 */
class NewsRepoImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepo {

    /**
     * Retrieves news articles from the data source.
     * @return A suspend function returning a flow of network response containing news articles.
     */
    override suspend fun getNewsArticles() = newsDataSource.getNewsData()
}