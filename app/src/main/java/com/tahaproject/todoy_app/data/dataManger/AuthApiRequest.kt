package com.tahaproject.todoy_app.data.dataManger

import android.util.Log
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IAuthApi
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.data.responses.RegisterResponse
import com.tahaproject.todoy_app.util.EndPoint

class AuthApiRequest(private val apiRequest: ApiRequest) : IAuthApi {

    override fun login(loginRequest: LoginRequest): LogInResponse {
        val request = apiRequest.getRequest(loginRequest, EndPoint.login)
        lateinit var result: LogInResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, LogInResponse::class.java)
            Log.i(ApiRequest.TAG_LOGIN, "$result")
        }
        return result
    }

    override fun register(registerRequest: RegisterRequest): RegisterResponse {
        val registerRequestLocal =
            RegisterRequest(registerRequest.username, registerRequest.password, BuildConfig.teamID)
        val request = apiRequest.postRequest(registerRequestLocal, EndPoint.signup)
        lateinit var result: RegisterResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, RegisterResponse::class.java)
            Log.i(ApiRequest.TAG_REGISTER, "$result")
        }
        return result
    }
}