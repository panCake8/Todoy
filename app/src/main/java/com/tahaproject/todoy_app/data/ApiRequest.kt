package com.tahaproject.todoy_app.data


import com.google.gson.Gson
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


open class ApiRequest {
    val gson = Gson()
    val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

   private fun makeRequest(endPoint: String,formData: Map<String, String> = emptyMap()): Request.Builder {
       val builder = Request.Builder()
           .url("${Constants.URL}/$endPoint")

       if (formData.isNotEmpty()) {
           val requestBodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
           for ((key, value) in formData) {
               requestBodyBuilder.addFormDataPart(key, value)
           }
           builder.post(requestBodyBuilder.build())
       }

       return builder
    }

    fun postRequest(body: FormBody, endPoint: String): Request =
        makeRequest(endPoint).post(
            body
        ).build()
    fun postFormDataRequest(formData: Map<String, String>, endPoint: String): Request =
        makeRequest(endPoint, formData).build()

    fun getRequest(endPoint: String): Request = makeRequest(endPoint).get().build()

    fun putRequest(body: FormBody, endPoint: String): Request =
        makeRequest(endPoint).put(
            body
        ).build()
}