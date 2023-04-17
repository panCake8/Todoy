package com.tahaproject.todoy_app.ui.login.presenter

import androidx.fragment.app.Fragment
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


interface LoginContract {
    interface IView{

        fun onSuccess(loginResponse: LoginResponse):Unit
        fun onFailRequest(error: IOException)
        fun showInvalidMassage(usernameMassage:String,passwordMassage:String)

    }

    interface IPresenter{

        fun fetchData(loginRequest: LoginRequest)
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(e: IOException)

        fun isValid(loginRequest: LoginRequest):Boolean



    }
}