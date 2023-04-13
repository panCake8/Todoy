package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.domain.requests.LoginRequest
import com.tahaproject.todoy_app.data.domain.responses.LoginResponse
import java.io.IOException


interface LoginContract {
    interface View {
        fun showData(contentLoginResponse: LoginResponse)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun fetchData(loginRequest: LoginRequest)
        fun attach(view: View)
        fun deAttach()
    }
}