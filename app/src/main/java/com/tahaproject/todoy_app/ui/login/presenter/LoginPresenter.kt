package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.ILoginApi
import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApi
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.responses.LoginResponse
import java.io.IOException


class LoginPresenter(private val view: ILoginContract.ILoginView) :
    ILoginContract.ILoginPresenter {
    private val loginRequestApiImpl: ILoginApi = LoginApi()
    override fun fetchData(loginRequest: LoginRequest) {
        loginRequestApiImpl.login(loginRequest, ::showData, ::showError)
    }


    private fun showData(loginResponse: LoginResponse) {
        view.showData(loginResponse)
    }


    private fun showError(ioException: IOException) {
        view.showError(ioException)
    }

}