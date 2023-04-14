package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApiRequestImpl
import com.tahaproject.todoy_app.data.domain.requests.SignUpRequest
import com.tahaproject.todoy_app.ui.signup.SignUpFragment

class SignupPresenter(signUpFragment: SignUpFragment) :SignupContract.Presenter{
    private var view: SignupContract.View? = null
    private val signUpApiRequestImpl= SignUpApiRequestImpl()
    override fun fetchData(signUpRequest: SignUpRequest) {

        view?.let { view ->
            signUpApiRequestImpl.signUp(signUpRequest, { signupResponse ->
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