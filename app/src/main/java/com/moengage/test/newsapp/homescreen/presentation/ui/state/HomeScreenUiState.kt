package com.moengage.test.newsapp.homescreen.presentation.ui.state

import androidx.compose.runtime.Stable
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse

@Stable
data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val newsArticles: List<NewsArticleResponse.Article?> = listOf(),
)