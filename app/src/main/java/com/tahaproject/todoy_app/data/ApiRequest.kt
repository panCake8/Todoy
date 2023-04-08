package com.tahaproject.todoy_app.data

import android.util.Log
import com.google.gson.Gson
import com.tahaproject.todoy_app.data.requests.*
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.EndPoint
import com.tahaproject.todoy_app.util.HttpMethods
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


    private fun createRequest(endPoint: String): Request.Builder {
        return Request.Builder()
            .url("${Constants.url}/$endPoint")
            .header(Constants.authHeader)
    }

    private fun postRequest(body: Any, endPoint: String): Request =
        createRequest(endPoint).post(
            Gson().toJson(body).toRequestBody(Constants.applicationJson.toMediaTypeOrNull())
        ).build()

    private fun getRequest(body: Any, endPoint: String): Request = createRequest(endPoint).get().build()

    private fun putRequest(body: Any, endPoint: String): Request =
        createRequest(endPoint).put(
            Gson().toJson(body).toRequestBody("application/json".toMediaTypeOrNull())
        ).build()


    override fun login(): LogInResponse {
        val loginRequest = LoginRequest("", "")
        val request = getRequest(loginRequest, EndPoint.login)
        lateinit var result: LogInResponse
        // execute the request and handle the response
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    result = gson.fromJson(jsonString, LogInResponse::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })
        return result
    }

    override fun register(): RegisterResponse {
        val registerRequest = RegisterRequest("", "", Constants.teamID)
        val request = postRequest(registerRequest, EndPoint.signup)
        lateinit var result: RegisterResponse
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    result = gson.fromJson(jsonString, RegisterResponse::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })
        return result
    }

    override fun createPersonalTodo(): PersonalTodoCreateResponse{
        val request = PersonalTodoRequest("", "")
        postRequest(request, EndPoint.personalTodo)
        lateinit var result: PersonalTodoCreateResponse
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    result = gson.fromJson(jsonString, PersonalTodoCreateResponse::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })
        return result

    }

    override fun getPersonalTodos() :PersonalTodo{
        getRequest("",EndPoint.personalTodo)
        lateinit var result: PersonalTodo
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    result = gson.fromJson(jsonString, PersonalTodo::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })
        return result

    }

    override fun updatePersonalTodosStatus() :PersonalTodoUpdateResponse{
        val request = PersonalTodoUpdateRequest("", 0)
        postRequest(request, EndPoint.personalTodo)
        lateinit var result: PersonalTodoUpdateResponse
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    result = gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java)
                    Log.i(TAG_LOGIN, "$result")
                }
                // handle the response
            }
        })
        return result

    }

    override fun createTeamTodo() {
        val request = TeamToDoPostRequest("", "", "")
        postRequest(request, EndPoint.teamTodo)
    }

    override fun getTeamTodos() {
        getRequest(EndPoint.teamTodo)
    }

    override fun updateTeamTodosStatus() {
        val request = PersonalTodoUpdateRequest("", 0)
        postRequest(request, EndPoint.teamTodo)
    }

    companion object {
        const val TAG_LOGIN = "Login_Tag"
    }
}