package com.moengage.test.newsapp.homescreen.presentation.ui.state

import androidx.compose.runtime.Stable
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse


/**
 * Represents the UI state of the home screen.
 * @property isLoading Indicates whether data is currently being loaded.
 * @property newsArticles The list of news articles to display.
 */
@Stable
data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val newsArticles: List<NewsArticleResponse.Article?> = listOf(),
)