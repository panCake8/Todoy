package com.tahaproject.todoy_app.ui.presenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException


interface IHomeContract {
    interface IView {
        fun navigateToLoginScreen()
        fun showPersonalData(personalData: ToDosResponse)
        fun showTeamData(teamData: ToDosResponse)

        fun showError(ioException: IOException)
    }

    interface IPresenter {
        fun fetchPersonalData()
        fun fetchTeamData()
        fun onUnauthorizedError()
    }
}