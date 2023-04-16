package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException

interface ILoginContract {
    interface View{

    }

    interface Presenter{

        fun getLoginResponse(loginResponse: LoginResponse)
        fun onFailRequest(error: IOException)

    }
}