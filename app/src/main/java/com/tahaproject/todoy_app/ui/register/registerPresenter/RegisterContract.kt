package com.tahaproject.todoy_app.ui.register.registerPresenter

interface RegisterContract {
    interface IView {
        fun navigateToLoginScreen()
    }

    interface IPresenter {
    fun onLoginScreen()
    }
}