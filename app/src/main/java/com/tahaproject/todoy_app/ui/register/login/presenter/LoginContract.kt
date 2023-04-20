package com.tahaproject.todoy_app.ui.register.login.presenter

import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


interface LoginContract {
    interface IView {
        fun onSuccess()
        fun onFailRequest(error: IOException)
        fun showInvalidUserNameMassage(userNameMessage: String)
        fun showInvalidPasswordMassage(passwordMessage: String)
        fun getToken(token: String)
    }

    interface IPresenter {
        fun fetchData(userName: String, password: String)
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(e: IOException)
        fun isValid(userName: String, password: String): Boolean
    }
}