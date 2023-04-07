package com.tahaproject.todoy_app.data

import com.google.gson.Gson
import com.tahaproject.todoy_app.data.requests.*
import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.EndPoint
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class ApiRequest(private val gson: Gson) : IRequestApis {

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(logInterceptor)
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
        getRequest(EndPoint.login)

    }

    override fun register() {
        var request =RegisterRequest("","")
        postRequest(request, EndPoint.signup)
    }

    override fun createPersonalTodo() {
        var request =PersonalTodoRequest("","")
        postRequest(request, EndPoint.personalTodo)
    }

    override fun getPersonalTodos() {
       getRequest(EndPoint.personalTodo)
    }

    override fun updatePersonalTodosStatus() {
        var request =PersonalTodoUpdateRequest("",0)
        postRequest(request, EndPoint.personalTodo)
    }

    override fun createTeamTodo() {
        var request =TeamToDoPostRequest("","","")
        postRequest(request, EndPoint.teamTodo)
    }

    override fun getTeamTodos() {
        getRequest(EndPoint.teamTodo)
    }

    override fun updateTeamTodosStatus() {
        var request =PersonalTodoUpdateRequest("",0)
        postRequest(request, EndPoint.teamTodo)
    }
}