package com.tahaproject.todoy_app.ui.signup.presenter

import com.tahaproject.todoy_app.data.domain.requests.SignUpRequest
import com.tahaproject.todoy_app.data.domain.responses.SignUpResponse
import java.io.IOException

interface SignupContract{
    interface View {
        fun showData(signUpResponse: SignUpResponse)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun fetchData(signUpRequest: SignUpRequest)
        fun attach(view: View)
        fun deAttach()
    }
}