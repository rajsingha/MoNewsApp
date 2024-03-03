package com.moengage.test.core.network.base

import com.moengage.test.core.network.exception_handler.ApiFailureException

/**
 * Sealed class representing different network responses.
 * @param T The type of data returned in the response.
 */
sealed class NetworkResponse<out T> {
    /**
     * Represents a successful network response.
     * @param data The data returned in the response.
     */
    data class Success<out T>(val data: T) : NetworkResponse<T>()

    /**
     * Represents an error network response.
     * @param error The API failure exception.
     * @param errorData Additional error data, if any.
     */
    data class Error<out T>(
        val error: ApiFailureException? = null,
        val errorData: Any? = null
    ) : NetworkResponse<T>()

    /**
     * Represents a loading network response.
     * @param isLoading Indicates whether the network operation is in progress or not.
     */
    data class Loading<out T>(val isLoading: Boolean) : NetworkResponse<T>()
}