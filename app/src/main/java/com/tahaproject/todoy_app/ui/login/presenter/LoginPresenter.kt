package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest


class LoginPresenter :
    LoginContract.LoginPresenter {
    private var view: LoginContract.LoginView? = null
    private val loginRequestApiImpl = LoginApi()
    override fun fetchData(loginRequest: LoginRequest) {
        view?.let { view ->
            loginRequestApiImpl.login(loginRequest, { loginResponse ->
                view.showData(loginResponse)
            }, { ioException ->
                view.showError(ioException)
            })
        }
    }

    override fun attach(loginView: LoginContract.LoginView) {
        this.view = loginView
    }

    override fun deAttach() {
        view = null
    }

}