package com.tahaproject.todoy_app.data

import android.nfc.Tag
import android.util.Log
import com.google.gson.Gson
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class ApiRequest(private val gson: Gson) : IRequestApis {

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()
    private val token = Constants.token
    private fun postRequest(body: Any, endPoint: String): Request {
        return Request
            .Builder()
            .url("${Constants.url}/$endPoint")
            .post(
                Gson().toJson(body).toRequestBody("application/json".toMediaTypeOrNull())
            )
            .header("Authorization", "Bearer $token")
            .build()
    }

    private fun getRequest(endPoint: String): Request {
        return Request
            .Builder()
            .url("${Constants.url}/$endPoint")
            .get()
            .header("Authorization", "Bearer $token")
            .build()
    }


    override fun login() {
        val request = getRequest("login")
        // execute the request and handle the response
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val result = gson.fromJson(jsonString, LogInResponse::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })

    }

    override fun register() {
    }

    override fun createPersonalTodo() {
        TODO("Not yet implemented")
    }

    override fun getPersonalTodos() {
        TODO("Not yet implemented")
    }

    override fun updatePersonalTodosStatus() {
        TODO("Not yet implemented")
    }

    override fun createTeamTodo() {
        TODO("Not yet implemented")
    }

    override fun getTeamTodos() {
        TODO("Not yet implemented")
    }

    override fun updateTeamTodosStatus() {
        TODO("Not yet implemented")
    }

    companion object {
        const val TAG_LOGIN = "Login_Tag"
    }
}