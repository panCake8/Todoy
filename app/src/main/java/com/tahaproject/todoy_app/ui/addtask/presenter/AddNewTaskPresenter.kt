package com.tahaproject.todoy_app.ui.addtask.presenter

import com.tahaproject.todoy_app.data.apiManger.auth.login.LoginApiImpl
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApiImpl
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamTodoRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.ui.login.presenter.LoginContract

class AddNewTaskPresenter : AddNewTaskContract.Presenter {
    private var view: AddNewTaskContract.View? = null // Change val to var to allow updating the view
    private val personalTodoApiImpl = PersonalTodoApiImpl()
    private val teamTodoApiImpl = TeamTodoApiImpl() // Change the variable name to teamTodoApiImpl to match the class name

    override fun addPersonalTask(title: String, description: String) {
        val personalTodo = PersonalTodoRequest.PersonalTodo(
            id = "",
            title = title,
            description = description,
            status = 0, // for todo
            creationTime = ""
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
            id = "",
            title = title,
            description = description,
            assignee = assignee,
            status = 0, // for todo
            creationTime = ""
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