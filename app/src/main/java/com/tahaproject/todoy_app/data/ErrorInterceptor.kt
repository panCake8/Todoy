package com.tahaproject.todoy_app.data

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                handleResponseError(response)
            }
            return response
        } catch (e: IOException) {
            handleNetworkError(e)
            throw e
        }
    }

    private fun handleResponseError(response: Response) {
        // Handle response errors
    }

    private fun handleNetworkError(e: IOException) {
        when (e) {
            is ConnectException -> {
                // Handle connection error
            }
            is SocketTimeoutException -> {
                // Handle timeout error
            }
            is UnknownHostException -> {
                // Handle host not found error
            }
            else -> {
                // Handle generic IO exception
            }
        }
    }
}
