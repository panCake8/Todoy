package com.tahaproject.todoy_app.ui.todo.details.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.ui.addtask.presenter.IAddNewTaskContract
import java.io.IOException

class IDetailsPresenter
    (private val view: IDetailsContract.View,
    private val personalTodoApi: IPersonalTodoApi,
    private val teamTodoApi: ITeamTodoApi
    )

    : IDetailsContract.IPresenter {
    override fun updateTeamTodoTask(
        teamTodoUpdateRequest: UpdateTodoTask
    ) {
        teamTodoApi
            .updateTeamTodosStatus(
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
        view.showTaskUpdated(successMessage)
    }

    private fun onTaskFailed(error: IOException) {
        view.showError(error)
    }
}

