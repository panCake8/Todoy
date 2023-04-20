package com.tahaproject.todoy_app.ui.home.homePresenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException


interface HomeContract {
    interface IView {
        fun showPersonalToDoData(personalTodoResponse: ToDosResponse)
        fun showTeamToDoData(teamTodoResponse: ToDosResponse)
        fun showChart()

        fun showError(ioException: IOException)
    }

    interface IPresenter {
        fun fetchPersonalData()
        fun fetchTeamData()
    }
}