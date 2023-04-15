package com.tahaproject.todoy_app.ui.presenter

import java.io.IOException


interface IHomeContract {
    interface IView {
        fun navigateToLoginScreen()
        fun navigateToHomeScreen()
        fun showError(ioException: IOException)
    }

    interface IPresenter {
        fun fetchData()
        fun onUnauthorizedError()
        fun onHome()
    }
}