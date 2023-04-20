package com.tahaproject.todoy_app.ui.todo.details.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import java.io.IOException

class IDetailsPresenter
    (
    private val IView: IDetailsContract.IView,
    token: String
) : IDetailsContract.IPresenter {
    private val personalTodoApi: IPersonalTodoApi = PersonalTodoApi(token)
    private val teamTodoApi: ITeamTodoApi = TeamTodoApi(token)
    override fun updateTeamTodoTask(
        teamTodoUpdateRequest: UpdateTodoTask
    ) {
        teamTodoApi.updateTeamTodosStatus(
            teamTodoUpdateRequest,
            ::onTaskSuccess,
            ::onTaskFailed
        )
    }

    override fun updatePersonalTodoTask(
        personalTodoUpdateRequest: UpdateTodoTask
    ) {
        personalTodoApi.updatePersonalTodosStatus(
            personalTodoUpdateRequest,
            ::onTaskSuccess,
            ::onTaskFailed
        )

    }

    private fun onTaskSuccess(successMessage: String) {
        IView.showTaskUpdated(successMessage)
    }

    private fun onTaskFailed(error: IOException) {
        IView.showError(error)
    }
}

