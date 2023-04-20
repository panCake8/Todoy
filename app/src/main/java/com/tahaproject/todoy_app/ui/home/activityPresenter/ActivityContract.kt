package com.tahaproject.todoy_app.ui.home.activityPresenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException


interface ActivityContract {
    interface IView {
        fun showPersonalToDoData(personalTodoResponse: ToDosResponse)
        fun navigateToLoginScreen()
        fun navigateToHomeScreen()
        fun noInternet()
        fun showError(ioException: IOException)
        fun serverError()
        fun showErrorImage()
        fun hideErrorImage()
    }

    interface IPresenter {
        fun fetchPersonalData()
        fun onHome()
        fun onLogin()
    }
}