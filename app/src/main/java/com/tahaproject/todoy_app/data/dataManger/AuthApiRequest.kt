package com.tahaproject.todoy_app.data.dataManger
import ApiCallback
import android.util.Log
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IAuthApi
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.data.responses.RegisterResponse
import com.tahaproject.todoy_app.util.EndPoint
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class AuthApiRequest(private val apiRequest: ApiRequest) : IAuthApi {



    override fun login(loginRequest: LoginRequest,
                       onSuccess: (LogInResponse) -> Unit,
                       onFailure: (Throwable) -> Unit) {
        val request = apiRequest.postRequest(loginRequest, EndPoint.login)
         apiRequest.client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, LogInResponse::class.java))
                    Log.i(ApiRequest.TAG_LOGIN, "$jsonString")
                }
            }

        })
    }

    override fun register(registerRequest: RegisterRequest,
                          onSuccess: (RegisterResponse) -> Unit,
                          onFailure: (Throwable) -> Unit) {
        val registerRequestLocal =
            RegisterRequest(registerRequest.username, registerRequest.password, BuildConfig.teamID)
        val request = apiRequest.postRequest(registerRequestLocal, EndPoint.signup)
        apiRequest.client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, RegisterResponse::class.java))
                    Log.i(ApiRequest.TAG_REGISTER, "$jsonString")
                }
            }
        })
    }

}