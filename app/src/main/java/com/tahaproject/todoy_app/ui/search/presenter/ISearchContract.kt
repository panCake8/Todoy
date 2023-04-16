package com.tahaproject.todoy_app.ui.search.presenter

import com.tahaproject.todoy_app.data.requests.SignUpRequest
import com.tahaproject.todoy_app.data.responses.SignUpResponse
import java.io.IOException

interface ISearchContract {
    interface IView {
        fun showData(signUpResponse: SignUpResponse)
        fun showError(error: IOException)
    }

    interface IPresenter {
        fun fetchData(signUpRequest: SignUpRequest)
    }
}