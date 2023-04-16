package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApiImpl
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException

class LoginPresenter():ILoginContract.Presenter  {
//    private val loginView:ILoginContract.View
    private val api  =LoginApiImpl()
    private var view :ILoginContract.View?=null
    override fun getLoginResponse(loginResponse: LoginResponse) {

    }

    override fun onFailRequest(error: IOException) {

    }
    fun attach(LoginView:ILoginContract.View){
        this.view =LoginView
    }

}