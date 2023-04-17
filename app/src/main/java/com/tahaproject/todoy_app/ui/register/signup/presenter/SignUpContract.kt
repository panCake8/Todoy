package com.tahaproject.todoy_app.ui.register.signup.presenter

import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import java.io.IOException

interface SignUpContract {
    interface View {
        fun showSuccessMessage(message:String)
        fun showErrorMessage(error: IOException)
    }

    interface Presenter {
        fun fetchData(signUpRequest: SignUpRequest)
    }
}