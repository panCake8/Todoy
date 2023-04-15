package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.signup.ISignUpApi
import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApi
import com.tahaproject.todoy_app.data.requests.SignUpRequest
import com.tahaproject.todoy_app.data.responses.SignUpResponse
import java.io.IOException

class SignUpPresenter(private val view: ISignUpContract.IView) : ISignUpContract.IPresenter {
    private val signUpApi: ISignUpApi = SignUpApi()
    override fun fetchData(signUpRequest: SignUpRequest) {
        signUpApi.signUp(signUpRequest, ::showData, ::showError, this)
    }


    private fun showData(signUpResponse: SignUpResponse) {
        view.showData(signUpResponse)
    }


    private fun showError(ioException: IOException) {
        view.showError(ioException)
    }
}