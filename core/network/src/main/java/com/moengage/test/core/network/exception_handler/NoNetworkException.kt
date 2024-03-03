package com.moengage.test.core.network.exception_handler

import java.io.IOException

/**
 * [NoNetworkException] Custom Exception class for No Internet Connectivity
 * */
open class NoNetworkException : IOException() {

    override fun getLocalizedMessage(): String? = "No Internet Connection"
}