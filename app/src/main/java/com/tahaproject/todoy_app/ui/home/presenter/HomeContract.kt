package com.tahaproject.todoy_app.ui.presenter

import com.tahaproject.todoy_app.data.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.responses.TeamTodoUpdateResponse
import java.io.IOException


interface IHomeContract {
    interface IView {
        fun showPersonalToDoData(personalTodosResponse: PersonalTodosResponse)
        fun showTeamToDoData(teamTodoUpdateResponse: TeamTodoUpdateResponse)
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