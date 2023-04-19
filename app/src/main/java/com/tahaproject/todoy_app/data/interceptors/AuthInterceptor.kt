package com.tahaproject.todoy_app.data.interceptors

import com.tahaproject.todoy_app.util.ErrorMessage
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 401) {
            // Handle the error 401 (unauthorized) here
            throw IOException(ErrorMessage.UNAUTHORIZED)
        }

        return response
    }
}