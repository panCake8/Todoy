package com.tahaproject.todoy_app.ui.addtask.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApiImpl
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamTodoRequest

class AddNewTaskPresenter : AddNewTaskContract.Presenter {
    private var view: AddNewTaskContract.View? = null
    private val personalTodoApiImpl = PersonalTodoApiImpl()
    private val teamTodoApiImpl = TeamTodoApiImpl()

    companion object {
        private const val DEFAULT_ID = ""
        private const val DEFAULT_STATUS = 0
        private const val DEFAULT_CREATION_TIME = ""
    }

    override fun addPersonalTask(title: String, description: String) {
        val personalTodo = PersonalTodoRequest.PersonalTodo(
            id = DEFAULT_ID,
            title = title,
            description = description,
            status = DEFAULT_STATUS, // for todo
            creationTime = DEFAULT_CREATION_TIME
        )
        val personalTodoRequest = PersonalTodoRequest(personalTodo, null, false)

        personalTodoApiImpl.createPersonalTodo(
            personalTodoRequest,
            onSuccess = { response ->
                view?.showTaskAdded(response)
            },
            onFailed = { ioException ->
                view?.showError(ioException)
            }
        )
    }

    override fun addTeamTask(title: String, description: String, assignee: String) {
        val teamTodo = TeamTodoRequest.TeamTodo(
            id = DEFAULT_ID,
            title = title,
            description = description,
            assignee = assignee,
            status = DEFAULT_STATUS, // for todo
            creationTime = DEFAULT_CREATION_TIME
        )
        val teamTodoRequest = TeamTodoRequest(teamTodo, null, false)

        teamTodoApiImpl.createTeamTodo(
            teamTodoRequest,
            onSuccess = { response ->
                view?.showTaskAdded(response)
            },
            onFailed = { ioException ->
                view?.showError(ioException)
            }
        )
    }
}