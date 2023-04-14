package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApiImpl
import com.tahaproject.todoy_app.data.domain.requests.LoginRequest


class LoginPresenter :
    LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val loginRequestApiImpl=LoginApiImpl()
    override fun fetchData(loginRequest: LoginRequest) {
        view?.let { view ->
            loginRequestApiImpl.login(loginRequest, { loginResponse ->
                view.showData(loginResponse)
            }, { ioException ->
                view.showError(ioException)
            })
        }
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    override fun deAttach() {
        view = null
    }

}