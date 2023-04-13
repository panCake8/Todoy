package com.tahaproject.todoy_app.ui.login.presenter

import com.tahaproject.todoy_app.data.responses.ContentLoginResponse

 interface LoginContract {
    interface View {
        fun showData(contentLoginResponse: ContentLoginResponse)
        fun showError(error: String)
    }

    interface Presenter {
        fun onSuccess(contentLoginResponse: ContentLoginResponse)
        fun onFailure(error: String)
    }
}