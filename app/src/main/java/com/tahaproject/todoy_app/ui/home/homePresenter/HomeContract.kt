package com.tahaproject.todoy_app.ui.presenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import java.io.IOException


interface IHomeContract {
    interface IView {
        fun showPersonalToDoData(personalTodoResponse : ToDosResponse)
        fun showTeamToDoData(teamTodoResponse : ToDosResponse)
        fun navigateToLoginScreen()
        fun navigateToHomeScreen()
        fun showError(ioException: IOException)
    }

    interface IPresenter {
        fun fetchPersonalData()
        fun fetchTeamData()
        fun onUnauthorizedError()
        fun onHome()
    }
}