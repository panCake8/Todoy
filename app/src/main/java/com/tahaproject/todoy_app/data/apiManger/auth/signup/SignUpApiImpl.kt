package com.tahaproject.todoy_app.data.apiManger.auth.signup

import android.util.Log
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.domain.requests.SignUpRequest
import com.tahaproject.todoy_app.data.domain.responses.SignUpResponse
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class SignUpApiImpl : ApiRequest(), ISignUpApi {

    override fun signUp(
        signUpRequest: SignUpRequest, onSuccess: (SignUpResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(USER_NAME, signUpRequest.username)
            .add(PASSWORD, signUpRequest.password)
            .add(TEAM_ID, signUpRequest.teamId)
            .build()
        val request = postRequest(formBody, Constants.EndPoints.signup)
        val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val signUpResponse =
                        gson.fromJson(jsonString, SignUpResponse::class.java)
                    Log.i("TAG_SIGNUP", signUpResponse.toString())
                    onSuccess(signUpResponse)
                }
            }
        })
    }

    companion object {
        const val USER_NAME = "username"
        const val PASSWORD = "password"
        const val TEAM_ID = "teamId"
    }


}