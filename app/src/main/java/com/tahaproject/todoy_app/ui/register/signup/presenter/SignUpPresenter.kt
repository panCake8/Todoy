package com.tahaproject.todoy_app.ui.register.signup.presenter

import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.signup.ISignUpApi
import com.tahaproject.todoy_app.data.apiManger.auth.signup.SignUpApi
import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import com.tahaproject.todoy_app.ui.register.signup.SignUpFragment
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.ErrorMessage
import java.io.IOException

class SignUpPresenter(private val view: SignUpContract.View) : SignUpContract.Presenter {
    private val signUpApi : ISignUpApi= SignUpApi()
    override fun fetchData(username: String, password: String,confirmPassword:String) {
        if(isValid(username,password,confirmPassword )){
            signUpApi.signUp(
                SignUpRequest(username,password,BuildConfig.teamID),
                ::onSignUpSuccess,
                ::onSignUpFailed
            )
        }
    }

    override fun onSignUpSuccess(message: String) {
       view.onSuccess()
    }

    override fun onSignUpFailed(e: IOException) {
        view.onFailedRequest(e)
    }

    override fun isValid(username: String, password: String, confirmPassword: String): Boolean {
        return if(!isUsernameValid(username)){
            view.showInvalidUserNameMessage(ErrorMessage.SHORT_USERNAME)
            false
        }else if (!isPasswordValid(password)){
            view.showInvalidPasswordMessage(ErrorMessage.SHORT_PASSWORD)
            false
        }else if(!isPasswordMatch(password,confirmPassword)){
            view.showNotMatchPasswordMessage(ErrorMessage.PASSWORD_NOT_MATCH)
            false
        }else{
            true
        }
    }
    private fun isUsernameValid(username: String): Boolean {
        return username.length >= Constants.SHORT_NAME
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= Constants.SHORT_PASSWORD
    }

    private fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

}