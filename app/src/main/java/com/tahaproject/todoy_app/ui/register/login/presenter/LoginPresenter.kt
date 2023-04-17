package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: LoginContract.IView) : LoginContract.IPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchData(loginRequest: LoginRequest) {
        if (isValid(loginRequest)) {
            loginRequestApiImpl.login(loginRequest, ::onLoginSuccess, ::onLoginFailed)
        }
    }

    private fun isValidUsername(username: String) = username.isNotEmpty()
    private fun isValidPassword(password: String) = password.isNotEmpty()

    override fun onLoginSuccess(result: LoginResponse) {
        view.onSuccess(result)
        view.getToken(result.value.token)
    }

    override fun onLoginFailed(e: IOException) {

    }

    override fun isValid(loginRequest: LoginRequest): Boolean {
        return if (!isValidUsername(loginRequest.username)) {
                    view.showInvalidMassage("please enter your username", "")
                    false
                }
                else if (!isValidPassword(loginRequest.password)) {
                    view.showInvalidMassage("", "please enter your password")
                    false
                }
                else {
                    true
                }
    }

}

