package com.moengage.test.newsapp.homescreen.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.core.ui.base.UiEvents
import com.moengage.test.core.utils.Utils.parseDate
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

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNewArticles()
    }

    private fun sortArticlesByOldToNew() {
        val sortedArticles = uiState.value.newsArticles.sortedBy { article ->
            parseDate(article?.publishedAt)
        }
       updateUiState(uiState.value.copy(newsArticles = sortedArticles))
    }

    private fun sortArticlesByNewToOld(){
        val sortedArticles =uiState.value.newsArticles.sortedByDescending { article ->
            parseDate(article?.publishedAt)
        }
        updateUiState(uiState.value.copy(newsArticles = sortedArticles))
    }
    fun onUiEvent(events: HomeScreenUiEvents){
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
                            it.data?.articles?.let {
                                updateUiState(uiState.value.copy(newsArticles = it))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateUiState(state: HomeScreenUiState) {
        _uiState.update { state }
    }
}