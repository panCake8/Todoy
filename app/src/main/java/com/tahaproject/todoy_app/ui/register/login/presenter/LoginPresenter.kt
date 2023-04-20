package com.tahaproject.todoy_app.ui.register.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: LoginContract.IView) : LoginContract.IPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchData(userName: String, password: String) {
        if (isValid(userName, password)) {
            loginRequestApiImpl.login(
                LoginRequest(userName, password),
                ::onLoginSuccess,
                ::onLoginFailed
            )
        }
    }

    private fun isValidUsername(userName: String) = userName.isNotEmpty()
    private fun isValidPassword(password: String) = password.isNotEmpty()

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        loginResponse.value.token?.let { view.getToken(it) }
        view.onSuccess()
    }

    override fun onLoginFailed(e: IOException) {
        view.onFailRequest(e)
    }

    override fun isValid(userName: String, password: String): Boolean {
        return if (!isValidUsername(userName)) {
            view.showInvalidUserNameMassage("please enter your userName")
            false
        } else if (!isValidPassword(password)) {
            view.showInvalidPasswordMassage("please enter your password")
            false
        } else {
            true
        }
    }

}

