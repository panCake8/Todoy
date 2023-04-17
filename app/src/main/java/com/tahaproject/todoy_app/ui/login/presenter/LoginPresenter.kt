package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: LoginContract.IView) : LoginContract.IPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchData(loginRequest: LoginRequest) {
        if (validate(loginRequest)){
            loginRequestApiImpl.login(loginRequest,::onLoginSuccess,::onLoginFailed)
        }
        else{
            validate(loginRequest)
        }
    }

    override fun onLoginSuccess(result: LoginResponse) {
      view?.onSuccess(result)
    }
    override fun onLoginFailed(e: IOException) {

    }

    override fun validate(loginRequest: LoginRequest): Boolean {
        return if(loginRequest.username.isNullOrEmpty()){
            view?.showInvalidMassage("please enter username","")
            false
        } else if(loginRequest.password.isNullOrEmpty()){
            view?.showInvalidMassage("","please enter password")
            false
        } else{
            true
        }

    }

}

