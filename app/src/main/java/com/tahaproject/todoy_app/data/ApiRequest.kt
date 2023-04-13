package com.tahaproject.todoy_app.data

import com.google.gson.Gson
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor


open class ApiRequest {
    val gson = Gson()

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    private fun createRequest(endPoint: String): Request.Builder {
        return Request.Builder()
            .url("${Constants.URL}/$endPoint")
            .header(Constants.AUTH, "${Constants.BEARER} ${Constants.TOKEN}")
    }

    fun postRequest(body: FormBody, endPoint: String): Request =
        createRequest(endPoint).post(
            body
        ).build()

    fun getRequest(endPoint: String): Request = createRequest(endPoint).get().build()

    fun putRequest(body: FormBody, endPoint: String): Request =
        createRequest(endPoint).put(
            body
        ).build()

//    companion object {
//        const val TAG_LOGIN = "Login_Tag"
//        const val TAG_REGISTER = "Register_Tag"
//        const val TAG_PERSONAL_GET = "Get_Personal_Tag"
//        const val TAG_PERSONAL_CREATE = "Create_Personal_Tag"
//        const val TAG_PERSONAL_UPDATE = "Update_Personal_Tag"
//        const val TAG_TEAM_GET = "Get_Team_Tag"
//        const val TAG_TEAM_CREATE = "Create_Team_Tag"
//        const val TAG_TEAM_UPDATE = "Update_Team_Tag"
//    }
}