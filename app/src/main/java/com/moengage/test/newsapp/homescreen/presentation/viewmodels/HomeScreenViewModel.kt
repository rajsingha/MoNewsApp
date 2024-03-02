package com.moengage.test.newsapp.homescreen.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.core.network.exception_handler.ApiFailureException
import com.moengage.test.newsapp.homescreen.domain.usecase.NewsUseCases
import com.moengage.test.newsapp.homescreen.presentation.ui.state.HomeScreenUiState
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


    private val _apiError = MutableSharedFlow<ApiFailureException?>()
    val apiError = _apiError.asSharedFlow()

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()


    fun getNewArticles() {
        Log.e("ViewModel","Hi")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getNewsArticleUseCase.invoke().collect {
                Log.e("ViewModel",it.toString())
                withContext(Dispatchers.Main.immediate) {
                    when (it) {
                        is NetworkResponse.Error -> {
                            _apiError.emit(it.error)
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
                            Log.e("ViewModel",it.data.toString())
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