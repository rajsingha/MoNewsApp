package com.moengage.test.core.network.exception_handler

import android.text.TextUtils

/**
 * Exception to manage the Network call failures
 * @param message failure msg
 * @param cause throwable
 * @param code is error code of the exception
 * */
open class ApiFailureException(
    message: String? = null,
    cause: Throwable? = null,
    errorCode: String? = null,
    code: Int? = null,
) :
    Exception(message) {
    private val _message: String? by lazy {
        if (!TextUtils.isEmpty(message)) {
            cause?.toString()
        } else {
            null
        }
    }

    private val _code: Int? by lazy {
        code
    }

    private val _errorCode: String? by lazy {
        errorCode
    }

    val code get() = _code

    val errorCode get() = _errorCode


    override val message get() = _message ?: super.message

    init {
        if (cause != null) {
            super.initCause(cause)
        }
    }
}