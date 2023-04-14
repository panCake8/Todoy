package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApiImpl
import com.tahaproject.todoy_app.data.models.requests.SignUpRequest

class SignUpPresenter : SignUpContract.Presenter {
    private var view: SignUpContract.View? = null
    private val signUpApiImpl = SignUpApiImpl()
    override fun fetchData(signUpRequest: SignUpRequest) {
        view?.let { view ->
            signUpApiImpl.signUp(signUpRequest, { signUpResponse ->
                view.showData(signUpResponse)
            }, { ioException ->
                view.showError(ioException)
            }, this)
        }
    }

    override fun attach(signUpView: SignUpContract.View) {
        this.view = signUpView
    }

    override fun deAttach() {
        this.view = null
    }
}