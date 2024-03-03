package com.moengage.test.core.network.base

import com.moengage.test.core.network.exception_handler.ApiFailureException

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error<out T> constructor(
        val error: ApiFailureException? = null,
        val errorData: Any? = null
    ) : NetworkResponse<T>()
    data class Loading<out T>(val isLoading: Boolean) : NetworkResponse<T>()
}