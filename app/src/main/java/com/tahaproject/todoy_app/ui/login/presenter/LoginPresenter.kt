package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.responses.ContentLoginResponse

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun onSuccess(contentLoginResponse: ContentLoginResponse) {
        view.showData(contentLoginResponse)
    }

    override fun onFailure(error: String) {
        view.showError(error)
    }
}