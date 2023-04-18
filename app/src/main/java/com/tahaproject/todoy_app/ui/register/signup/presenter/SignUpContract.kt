package com.tahaproject.todoy_app.ui.register.signup.presenter

import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import java.io.IOException

interface SignUpContract {
    interface View {
        fun onSuccess()
        fun onFailedRequest(error: IOException)
        fun showInvalidUserNameMessage(message: String)
        fun showInvalidPasswordMessage(message: String)
        fun showNotMatchPasswordMessage(message: String)

    }

    interface Presenter {
        fun fetchData(username: String,password:String,confirmPassword:String)
        fun onSignUpSuccess(message:String)
        fun onSignUpFailed(e:IOException)
        fun isValid(username: String,password:String,confirmPassword:String):Boolean

    }
}