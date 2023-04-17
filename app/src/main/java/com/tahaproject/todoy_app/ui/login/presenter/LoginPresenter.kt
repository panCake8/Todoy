package com.tahaproject.todoy_app.ui.login.presenter

import androidx.fragment.app.Fragment
import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: LoginContract.IView) : LoginContract.IPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchData(loginRequest: LoginRequest) {
        if (isValid(loginRequest)){
            loginRequestApiImpl.login(loginRequest,::onLoginSuccess,::onLoginFailed)
        }
        else{
            if(loginRequest.username.isNullOrEmpty()){

                view?.showInvalidMassage("please enter your username","")
            }
            else if(loginRequest.password.isNullOrEmpty()){
                view?.showInvalidMassage("","please enter your password",)
            }
        }
    }

    override fun onLoginSuccess(result: LoginResponse) {
      view?.onSuccess(result)
    }
    override fun onLoginFailed(e: IOException) {

    }

    override fun isValid(loginRequest: LoginRequest): Boolean {
        return loginRequest.username.isNotEmpty() &&
                loginRequest.password.isNotEmpty()

    }


}

