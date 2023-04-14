package com.tahaproject.todoy_app.data.interceptors

import android.content.Context
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response

class TodoInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferenceUtil = SharedPreferenceUtil(context).getToken()
        val request = chain.request().newBuilder()
            .header(Constants.AUTH, "${Constants.BEARER} $sharedPreferenceUtil").build()
        return chain.proceed(request)
    }
}