package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApiImpl
import com.tahaproject.todoy_app.data.domain.requests.SignUpRequest
import com.tahaproject.todoy_app.ui.signup.SignUpFragment

class SignupPresenter(signUpFragment: SignUpFragment) :SignupContract.Presenter{
    private var view: SignupContract.View? = null
    private val signUpApiImpl= SignUpApiImpl()
    override fun fetchData(signUpRequest: SignUpRequest) {

        view?.let { view ->
            signUpApiImpl.signUp(signUpRequest, { signupResponse ->
                view.showData(signupResponse)
            }, { ioException ->
                view.showError(ioException)
            })
        }
    }

    override fun attach(view: SignupContract.View) {
        this.view = view
    }

    override fun deAttach() {
        view = null
    }

}