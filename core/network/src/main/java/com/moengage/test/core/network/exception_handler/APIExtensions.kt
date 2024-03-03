package com.moengage.test.core.network.exception_handler

import com.moengage.test.core.network.base.NetworkResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

const val MAX_RETRIES = 3L
private const val INITIAL_BACKOFF = 2000L

/**
 * Calculates the backoff delay for retry attempts.
 * @param attempt The current attempt count.
 * @return The delay duration for the retry attempt.
 */
fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)

/**
 * Applies common side-effects to the flow to avoid repeating commonly used logic across the app.
 * @receiver The flow to which the side-effects are applied.
 * @return Flow with applied common side-effects.
 */
fun <T : Any> Flow<NetworkResponse<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < MAX_RETRIES) -> {
                delay(getBackoffDelay(attempt))
                true
            }
            else -> {
                false
            }
        }
    }.onStart { emit(NetworkResponse.Loading(true)) }
        .onCompletion { emit(NetworkResponse.Loading(false)) }

/**
 * Applies common side-effects to the flow for paging to avoid repeating commonly used logic across the app.
 * @receiver The flow to which the side-effects are applied.
 * @param forPaging Indicates whether the flow is used for paging or not.
 * @return Flow with applied common side-effects.
 */
fun <T : Any> Flow<NetworkResponse<T>>.applyCommonSideEffects(forPaging: Boolean) =
    retryWhen { cause, attempt ->
        when {
            (cause is IOException && attempt < MAX_RETRIES) -> {
                delay(getBackoffDelay(attempt))
                true
            }

            else -> {
                false
            }
        }
    }.onStart {
        if (!forPaging) {
            emit(NetworkResponse.Loading(true))
        }
    }.onCompletion {
        if (!forPaging) {
            emit(NetworkResponse.Loading(false))
        }
    }