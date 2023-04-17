package com.tahaproject.todoy_app.ui.register.registerPresenter

class RegisterPresenter(private val view:RegisterContract.IView):RegisterContract.IPresenter {
    override fun onLoginScreen() {
        view.navigateToLoginScreen()
    }
}