package com.tahaproject.todoy_app.ui.register.signup.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApi
import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import java.io.IOException

class SignUpPresenter(private val view: SignUpContract.View) : SignUpContract.Presenter {
    private val signUpApi = SignUpApi()
    override fun fetchData(signUpRequest: SignUpRequest) {
        signUpApi.signUp(signUpRequest, ::showSuccessMessage, ::showErrorMessage)
    }

    private fun showSuccessMessage(message: String) {
        view.showSuccessMessage(message)
    }

    private fun showErrorMessage(error: IOException) {
        view.showErrorMessage(error)
    }
}