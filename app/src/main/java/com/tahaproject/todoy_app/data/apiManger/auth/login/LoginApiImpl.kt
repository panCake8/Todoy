package com.tahaproject.todoy_app.data.apiManger.auth.login

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class LoginApiImpl : ApiRequest() {
    val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    fun login(
        loginRequest: LoginRequest,
        onFailRequest: (e: IOException) -> Unit,
        getResponse: (loginResponse: LoginResponse) -> LoginResponse
    ) {
        val credential = Credentials.basic(loginRequest.username, loginRequest.password)

        val request = getRequest(Constants.URL + Constants.EndPoints.login).newBuilder()
            .addHeader(Constants.AUTH, credential).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailRequest(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string().let { jsonString ->
                        val signUpResponse = gson.fromJson(jsonString, LoginResponse::class.java)
                        getResponse(signUpResponse)
                    }
                }
            }

        })
    }

}