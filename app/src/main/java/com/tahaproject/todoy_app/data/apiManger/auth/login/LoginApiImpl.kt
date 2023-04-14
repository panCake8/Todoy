package com.tahaproject.todoy_app.data.apiManger.auth.login

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.domain.requests.LoginRequest
import com.tahaproject.todoy_app.data.domain.responses.LoginResponse
import com.tahaproject.todoy_app.data.interceptors.LoginInterceptor
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class LoginApiImpl : ApiRequest(), ILoginApi {
    override fun login(
        loginRequest: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val credentials = Credentials.basic(loginRequest.username, loginRequest.password)
        val loginClient = OkHttpClient.Builder().addInterceptor(LoginInterceptor(credentials))
            .addInterceptor(logInterceptor)
            .build()
        val request = getRequest(Constants.EndPoints.login)
        loginClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val loginResponse =
                        gson.fromJson(jsonString, LoginResponse::class.java)
                    onSuccess(loginResponse)
                }
            }

        })
    }
}