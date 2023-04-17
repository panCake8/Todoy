package com.tahaproject.todoy_app.ui.login.presenter




import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse

import java.io.IOException


interface ILoginContract {
    interface ILoginView {
        fun showData(loginResponse: LoginResponse)
        fun showError(error: IOException)
    }

    interface ILoginPresenter {
        fun fetchData(loginRequest: LoginRequest)
    }
}