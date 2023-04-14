package com.tahaproject.todoy_app.data.interceptors

import com.tahaproject.todoy_app.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor(private val auth: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(Constants.AUTH, auth).build()
        return chain.proceed(request)
    }
}