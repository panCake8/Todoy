package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApiImpl
import com.tahaproject.todoy_app.data.domain.requests.LoginRequest


class LoginLoginPresenter :
    LoginContract.LoginPresenter {
    private var view: LoginContract.LoginView? = null
    private val loginRequestApiImpl = LoginApiImpl()
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