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

class LoginApi : ApiRequest(), ILoginApi {
    override val client: OkHttpClient
        get() = OkHttpClient.Builder().addInterceptor(logInterceptor).build()

    override fun login(
        loginRequest: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val credentials = Credentials.basic(loginRequest.username, loginRequest.password)
        val request = getLoginRequest(Constants.EndPoints.login, credentials)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string().let { jsonString ->
                        val loginResponse = gson.fromJson(jsonString, LoginResponse::class.java)
                        onSuccess(loginResponse)
                    }

                }

            }

        })
    }


}