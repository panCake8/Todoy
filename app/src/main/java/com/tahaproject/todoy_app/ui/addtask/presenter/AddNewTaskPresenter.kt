package com.tahaproject.todoy_app.ui.addtask.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApiImpl
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamTodoRequest
import com.tahaproject.todoy_app.util.Constants

class AddNewTaskPresenter(
    private val view: AddNewTaskContract.View,
    private val personalTodoApi: PersonalTodoApiImpl,
    private val teamTodoApi: TeamTodoApiImpl
) : AddNewTaskContract.Presenter {

    override fun addPersonalTask(title: String, description: String) {
        val personalTodo = PersonalTodoRequest.PersonalTodo(
            id = DEFAULT_ID,
            title = title,
            description = description,
            status = TODO_STATUS,
            creationTime = DEFAULT_CREATION_TIME
        )
        val personalTodoRequest = PersonalTodoRequest(personalTodo, Constants.ADDED, true)

        personalTodoApi.createPersonalTodo(personalTodoRequest, ::onTaskSuccess, ::onTaskFailed)
    }

    override fun addTeamTask(title: String, description: String, assignee: String) {
        val teamTodo = TeamTodoRequest.TeamTodo(
            id = DEFAULT_ID,
            title = title,
            description = description,
            assignee = assignee,
            status = TODO_STATUS,
            creationTime = DEFAULT_CREATION_TIME
        )

        val teamTodoRequest = TeamTodoRequest(teamTodo, Constants.ADDED, true)

        teamTodoApi.createTeamTodo(teamTodoRequest, ::onTaskSuccess, ::onTaskFailed)
    }

    private fun onTaskSuccess(successMessage: String) {
        view.showTaskAdded(successMessage)
    }

    private fun onTaskFailed(throwable: Throwable) {
        view.showError(throwable)
    }

    companion object {
        private const val DEFAULT_ID = ""
        private const val TODO_STATUS = 0
        private const val DEFAULT_CREATION_TIME = ""
    }
}