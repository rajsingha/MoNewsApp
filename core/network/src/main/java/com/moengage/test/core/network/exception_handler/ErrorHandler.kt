package com.moengage.test.core.network.exception_handler

import android.net.sip.SipErrorCode
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.moengage.test.core.network.base.NetworkResponse
import com.mpokket.core.network.exception_handler.NoNetworkException
import java.net.ConnectException

object ErrorHandler {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    const val MAX_CHARS = 150
    const val NO_INTERNET_ERROR_CODE = 1
    const val NETWORK_ERROR_MESSAGE =
        "No internet connection, Please check you mobile data or Wi-fi"
    const val PLEASE_TRY_AGAIN = "Failed to connect to the server, Please try after some time"
    const val SOMETHING_WRONG_TRY_AGAIN = "Something went wrong. Please try again later!"
    const val PARSING_ERROR = "PARSING_ERROR"


    inline fun <reified T> handleException(
        exception: Exception
    ): NetworkResponse<T> {
        return when (exception) {
            is NoNetworkException -> {
                NetworkResponse.Error(
                    ApiFailureException(
                        NETWORK_ERROR_MESSAGE,
                        null,
                        code = NO_INTERNET_ERROR_CODE
                    )
                )
            }

            is ConnectException -> {
                NetworkResponse.Error(
                    ApiFailureException(
                        PLEASE_TRY_AGAIN,
                        null,
                        code = SipErrorCode.SERVER_ERROR
                    )
                )
            }

            is JsonSyntaxException -> {
                NetworkResponse.Error(
                    ApiFailureException(
                        message = PARSING_ERROR,
                        cause = null,
                        null
                    )
                )
            }

            else -> {
                NetworkResponse.Error(
                    ApiFailureException(
                        exception.localizedMessage,
                        exception,
                        null
                    )
                )
            }
        }
    }
}
