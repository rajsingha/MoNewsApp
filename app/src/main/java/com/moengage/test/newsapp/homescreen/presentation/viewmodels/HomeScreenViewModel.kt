package com.moengage.test.newsapp.homescreen.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.core.ui.base.UiEvents
import com.moengage.test.core.utils.Utils.parseDate
import com.moengage.test.core.utils.Utils.populateCleanName
import com.moengage.test.newsapp.homescreen.data.dto.NewsArticleResponse
import com.moengage.test.newsapp.homescreen.domain.usecase.NewsUseCases
import com.moengage.test.newsapp.homescreen.presentation.ui.state.HomeScreenUiState
import com.moengage.test.newsapp.homescreen.presentation.utils.HomeScreenUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel for the home screen.
 */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

    // Shared flow for emitting UI events
    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    // State flow for holding UI state
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNewArticles()
    }

    /**
     * Sort articles by old to new publication date.
     */
    private fun sortArticlesByOldToNew() {
        val sortedArticles = uiState.value.newsArticles.sortedBy { article ->
            parseDate(article?.publishedAt)
        }
        updateUiState(uiState.value.copy(newsArticles = sortedArticles))
    }

    /**
     * Sort articles by new to old publication date.
     */
    private fun sortArticlesByNewToOld() {
        val sortedArticles = uiState.value.newsArticles.sortedByDescending { article ->
            parseDate(article?.publishedAt)
        }
        updateUiState(uiState.value.copy(newsArticles = sortedArticles))
    }

    /**
     * Function to handle UI events.
     * @param events The UI event to handle.
     */
    fun onUiEvent(events: HomeScreenUiEvents) {
        viewModelScope.launch {
            when (events) {
                is HomeScreenUiEvents.OnTriggerUiEvents -> {
                    _uiEvents.emit(events.events)
                }

                HomeScreenUiEvents.OnOldArticlesSelected -> {
                    sortArticlesByOldToNew()
                }

                HomeScreenUiEvents.OnRecentArticlesSelected -> {
                    sortArticlesByNewToOld()
                }
            }
        }
    }

    /**
     * Fetch new articles through API.
     */
    private fun getNewArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getNewsArticleUseCase.invoke().collect {
                withContext(Dispatchers.Main.immediate) {
                    when (it) {
                        is NetworkResponse.Error -> {
                            _uiEvents.emit(UiEvents.ShowSnackBar(it.error?.message.orEmpty()))
                        }

                        is NetworkResponse.Loading -> {
                            updateUiState(
                                state =
                                uiState.value.copy(
                                    isLoading =
                                    it.isLoading
                                )
                            )
                        }

                        is NetworkResponse.Success -> {
                            it.data?.articles?.let { articles ->
                                cleanAndUpdateArticles(articles)
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Clean and update articles.
     * @param articles The list of articles to clean and update.
     */
    private fun cleanAndUpdateArticles(articles: List<NewsArticleResponse.Article?>) {
        val cleanedArticles = articles.map { article ->
            article?.copy(
                title = article.title?.trim().orEmpty(),
                description = article.description?.trim().orEmpty(),
                author = article.author?.let { author ->
                    val hasSpecialCharacters = author.any { !it.isLetterOrDigit() }
                    var cleanedAuthor = if (hasSpecialCharacters) {
                        ""
                    } else {
                        author
                    }
                    author.populateCleanName { firstName, lastName ->
                        cleanedAuthor = "$firstName $lastName"
                    }
                    cleanedAuthor
                },
            )
        }
        updateUiState(uiState.value.copy(newsArticles = cleanedArticles))
    }

    /**
     * Update UI state.
     * @param state The new UI state.
     */
    private fun updateUiState(state: HomeScreenUiState) {
        _uiState.update { state }
    }
}