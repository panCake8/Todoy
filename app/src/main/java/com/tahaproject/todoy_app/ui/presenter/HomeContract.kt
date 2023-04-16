package com.tahaproject.todoy_app.ui.presenter

import java.io.IOException


interface HomeContract {
    interface HomeView {
        fun navigateToLoginScreen()
        fun navigateToHomeScreen()
        fun showError(ioException: IOException)
    }

    interface HomePresenter {
        fun fetchPersonalData()
        fun fetchTeamData()
        fun attach(homeView: HomeView)
        fun deAttach()
        fun onUnauthorizedError()
        fun onHome()
    }
}