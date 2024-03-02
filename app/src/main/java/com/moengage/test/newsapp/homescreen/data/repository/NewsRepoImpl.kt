package com.moengage.test.newsapp.homescreen.data.repository

import com.moengage.test.newsapp.homescreen.data.datasource.NewsDataSource
import com.moengage.test.newsapp.homescreen.domain.repository.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : NewsRepo {

    override suspend fun getNewsArticles() = newsDataSource.getNewsData()
}