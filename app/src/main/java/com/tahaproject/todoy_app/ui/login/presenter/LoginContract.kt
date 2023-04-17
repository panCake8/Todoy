package com.tahaproject.todoy_app.ui.login.presenter
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


interface LoginContract {
    interface IView {
        fun getToken(token:String)
        fun showError(error: IOException)

        fun showMessage(message:String)
    }

    interface IPresenter {
        fun fetchToken(loginRequest: LoginRequest)

        fun validateUserName(userName:String)
    }
}