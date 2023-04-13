package com.tahaproject.todoy_app.data.apiManger

import android.util.Log
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IAuthApi
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.ContentLoginResponse
import com.tahaproject.todoy_app.ui.login.presenter.LoginContract
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthApiRequest(
    private val apiRequest: ApiRequest,
    private val loginContractPresenter: LoginContract.Presenter
) : IAuthApi {

    override fun login(loginRequest: LoginRequest) {
        val credentials = Credentials.basic(loginRequest.username, loginRequest.password)
        val request = Request.Builder().url("https://team-todo-62dmq.ondigitalocean.app/login")
            .header("Authorization", credentials)
            .get()
            .build()
//        val request = apiRequest.getRequest(Constants.EndPoints.login)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { loginContractPresenter.onFailure(it) }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val contentLoginResponse =
                        apiRequest.gson.fromJson(jsonString, ContentLoginResponse::class.java)
                    loginContractPresenter.onSuccess(contentLoginResponse)
                }

            }

        })

    }

    override fun register(registerRequest: RegisterRequest) {
        val formBody = FormBody.Builder().add(USER_NAME, registerRequest.username)
            .add(PASSWORD, registerRequest.password)
            .add(TEAM_ID, BuildConfig.teamID)
            .build()
        val request = Request.Builder().url("https://team-todo-62dmq.ondigitalocean.app/signup")
            .post(formBody)
            .build()
//        val request = apiRequest.postRequest(formBody, Constants.EndPoints.signup)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, RegisterResponse::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }

    companion object {
        const val USER_NAME = "username"
        const val PASSWORD = "password"
        const val TEAM_ID = "teamId"
    }
}