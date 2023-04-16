package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import java.io.IOException

interface ISignUpContract {
    interface IView {
        fun showData(signUpResponse: SignUpResponse)
        fun showError(error: IOException)
    }

    interface IPresenter {
        fun fetchData(signUpRequest: SignUpRequest)
    }
}