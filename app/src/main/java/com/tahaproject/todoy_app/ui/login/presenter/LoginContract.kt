package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.domain.requests.LoginRequest
import com.tahaproject.todoy_app.data.domain.responses.LoginResponse
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