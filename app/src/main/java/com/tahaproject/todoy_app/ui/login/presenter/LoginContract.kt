package com.tahaproject.todoy_app.ui.login.presenter
import LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


interface LoginContract {
    interface LoginView {
        fun showData(loginResponse: LoginResponse)
        fun showError(error: IOException)
    }

    interface LoginPresenter {
        fun fetchData(loginRequest: LoginRequest)
        fun attach(loginView: LoginView)
        fun deAttach()
    }
}