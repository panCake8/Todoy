package com.tahaproject.todoy_app.data.interceptors

import com.tahaproject.todoy_app.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TodoInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(Constants.AUTH, "${Constants.BEARER} $token").build()
        return chain.proceed(request)
    }
}