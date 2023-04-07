package com.tahaproject.todoy_app.data

import com.google.gson.Gson
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.util.Constants
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ApiRequest(private val gson: Gson) : IRequestApis {

    private val client = OkHttpClient()
    private val token = Constants.token
    private fun postRequest(body: Any, endPoint: String): Request {
        return Request
            .Builder()
            .url("${Constants.url}/$endPoint")
            .post(
                Gson().toJson(body).toRequestBody("application/json".toMediaTypeOrNull())
            )
            .header("Authorization", "Barear $token")
            .build()
    }

    private fun getRequest(endPoint: String): Request {
        return Request
            .Builder()
            .url("${Constants.url}/$endPoint")
            .get()
            .header("Authorization", "Barear $token")
            .build()
    }


    override fun login() {
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
}