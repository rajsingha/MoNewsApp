package com.moengage.test.core.network.service

import com.google.gson.Gson
import com.moengage.test.core.network.base.NetworkResponse
import com.moengage.test.core.network.exception_handler.ApiFailureException
import com.moengage.test.core.network.util.Constants.APPLICATION_JSON
import com.moengage.test.core.network.util.Constants.CONTENT_TYPE
import com.moengage.test.core.network.util.Constants.DELETE
import com.moengage.test.core.network.util.Constants.GET
import com.moengage.test.core.network.util.Constants.POST
import com.moengage.test.core.network.util.Constants.PUT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * NetworkManager, it uses singleton pattern to make api requests
 */
object NetworkManager {

    /**
     * Performs an HTTP GET request.
     * @param urlString The URL to send the request to.
     * @return A flow of [NetworkResponse] containing the response data or error.
     */
    inline fun <reified T> get(urlString: String): Flow<NetworkResponse<T?>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            val response = makeHttpRequest<T>(urlString, GET, null)
            emit(NetworkResponse.Loading(false))
            if (response?.first != null) {
                emit(NetworkResponse.Success(response.first))
            } else {
                emit(
                    NetworkResponse.Error(
                        ApiFailureException(
                            "Failed to get data",
                            code = response?.second
                        )
                    )
                )
            }
        }
    }

    /**
     * Performs an HTTP POST request.
     * @param urlString The URL to send the request to.
     * @param body The request body to send.
     * @return A flow of [NetworkResponse] containing the response data or error.
     */
    inline fun <reified T> post(urlString: String, body: Any): Flow<NetworkResponse<T?>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            val jsonBody = Gson().toJson(body)
            val response = makeHttpRequest<T>(urlString, POST, jsonBody)
            emit(NetworkResponse.Loading(false))
            if (response?.first != null) {
                emit(NetworkResponse.Success(response.first))
            } else {
                emit(
                    NetworkResponse.Error(
                        ApiFailureException(
                            "Failed to post data",
                            code = response?.second
                        )
                    )
                )
            }
        }
    }

    /**
     * Performs an HTTP PUT request.
     * @param urlString The URL to send the request to.
     * @param body The request body to send.
     * @return A flow of [NetworkResponse] containing the response data or error.
     */
    inline fun <reified T> put(urlString: String, body: Any): Flow<NetworkResponse<T?>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            val jsonBody = Gson().toJson(body)
            val response = makeHttpRequest<T>(urlString, PUT, jsonBody)
            emit(NetworkResponse.Loading(false))
            if (response?.first != null) {
                emit(NetworkResponse.Success(response.first))
            } else {
                emit(
                    NetworkResponse.Error(
                        ApiFailureException(
                            "Failed to put data",
                            code = response?.second
                        )
                    )
                )
            }
        }
    }

    /**
     * Performs an HTTP DELETE request.
     * @param urlString The URL to send the request to.
     * @return A flow of [NetworkResponse] containing the response data or error.
     */
    inline fun <reified T> delete(urlString: String): Flow<NetworkResponse<T?>> {
        return flow {
            emit(NetworkResponse.Loading(true))
            val response = makeHttpRequest<T>(urlString, DELETE, null)
            emit(NetworkResponse.Loading(false))
            if (response?.first != null) {
                emit(NetworkResponse.Success(response.first))
            } else {
                emit(
                    NetworkResponse.Error(
                        ApiFailureException(
                            "Failed to delete data",
                            code = response?.second
                        )
                    )
                )
            }
        }
    }

    /**
     * Makes an HTTP request.
     * @param urlString The URL to send the request to.
     * @param method The HTTP method to use (GET, POST, PUT, DELETE).
     * @param body The request body to send (for POST and PUT requests).
     * @return A pair containing the response data and the HTTP status code.
     */
    inline fun <reified T> makeHttpRequest(
        urlString: String,
        method: String,
        body: String?
    ): Pair<T?, Int>? {
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = method

            if (method == POST || method == PUT) {
                connection.doOutput = true
                connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON)
                val outputStream = DataOutputStream(connection.outputStream)
                outputStream.writeBytes(body)
                outputStream.flush()
                outputStream.close()
            }

            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            val responseCode = connection.responseCode
            val gson = Gson()
            val responseData = gson.fromJson<T>(stringBuilder.toString(), T::class.java)
            return Pair(responseData, responseCode)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection?.disconnect()
        }
        return Pair(null, -1)
    }
}
