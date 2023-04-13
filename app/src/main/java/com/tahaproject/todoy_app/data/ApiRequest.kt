package com.tahaproject.todoy_app.data


import com.google.gson.Gson
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


abstract class ApiRequest {
    val gson = Gson()
    val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

   private fun makeRequest(endPoint: String): Request.Builder {
        return Request.Builder()
            .url("${Constants.URL}/$endPoint")
    }

    fun postRequest(body: FormBody, endPoint: String): Request =
        makeRequest(endPoint).post(
            body
        ).build()

    fun getRequest(endPoint: String): Request = makeRequest(endPoint).get().build()

    fun putRequest(body: FormBody, endPoint: String): Request =
        makeRequest(endPoint).put(
            body
        ).build()
}