package com.tahaproject.todoy_app.ui.search.presenter

import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import java.io.IOException

interface ISearchContract {
    interface ISearchView {
        fun showData()
        fun showError(error: IOException)
    }

    interface ISearchPresenter {
        fun fetchData()
    }
}