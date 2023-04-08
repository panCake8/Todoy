package com.tahaproject.todoy_app.data.dataManger

import android.util.Log
import com.tahaproject.todoy_app.BuildConfig

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.ApiRequest.Companion.TAG_LOGIN
import com.tahaproject.todoy_app.data.IAuthApi
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.EndPoint


class AuthApi(private val apiRequest: ApiRequest) : IAuthApi {

    override fun login(): LogInResponse {
        val loginRequest = LoginRequest("", "")
        val request = apiRequest.getRequest(loginRequest, EndPoint.login)
        lateinit var result: LogInResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, LogInResponse::class.java)
            Log.i(TAG_LOGIN, "$result")
        }
        return result
    }

    override fun register(): RegisterResponse {
        val registerRequest = RegisterRequest("", "", BuildConfig.teamID)
        val request = apiRequest.postRequest(registerRequest, EndPoint.signup)
        lateinit var result: RegisterResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, RegisterResponse::class.java)
            Log.i(ApiRequest.TAG_REGISTER, "$result")
        }
        return result
    }
}