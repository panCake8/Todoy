package com.tahaproject.todoy_app.ui.register.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: LoginContract.IView) : LoginContract.IPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchToken(loginRequest: LoginRequest) {
        loginRequestApiImpl.login(loginRequest, ::getToken, ::showError)
    }

    override fun validateUserName(userName: String) {
        if (userName.isEmpty())
            view.showMessage("You should fill inputs")
    }

    private fun getToken(loginResponse: LoginResponse) {
        view.getToken(loginResponse.value.token.toString())
    }

    private fun showError(ioException: IOException) {
        view.showError(ioException)
    }

}