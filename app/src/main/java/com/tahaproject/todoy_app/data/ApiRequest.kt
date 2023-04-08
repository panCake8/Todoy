package com.tahaproject.todoy_app.data

import com.google.gson.Gson
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor


open class ApiRequest {
    val gson: Gson = Gson()
    private val token = BuildConfig.token
    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    private fun createRequest(endPoint: String): Request.Builder {
        return Request.Builder()
            .url("${BuildConfig.url}/$endPoint")
            .header(Constants.auth, "${Constants.bearer} $token")
    }

     fun postRequest(body: Any, endPoint: String): Request =
        createRequest(endPoint).post(
            Gson().toJson(body).toRequestBody(Constants.applicationJson.toMediaTypeOrNull())
        ).build()

     fun getRequest(body: Any, endPoint: String): Request = createRequest(endPoint).get().build()

     fun putRequest(body: Any, endPoint: String): Request =
        createRequest(endPoint).put(
            Gson().toJson(body).toRequestBody(Constants.applicationJson.toMediaTypeOrNull())
        ).build()
    companion object {
        const val TAG_LOGIN = "Login_Tag"
        const val TAG_REGISTER = "Register_Tag"
        const val TAG_PERSONAL_GET = "Get_Personal_Tag"
        const val TAG_PERSONAL_CREATE = "Create_Personal_Tag"
        const val TAG_PERSONAL_UPDATE = "Update_Personal_Tag"
        const val TAG_TEAM_GET = "Get_Team_Tag"
        const val TAG_TEAM_CREATE = "Create_Team_Tag"
        const val TAG_TEAM_UPDATE = "Update_Team_Tag"
    }
}