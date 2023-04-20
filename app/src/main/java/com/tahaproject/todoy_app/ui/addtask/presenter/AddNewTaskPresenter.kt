package com.tahaproject.todoy_app.ui.addtask.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import java.io.IOException

class AddNewTaskPresenter(
    private val view: IAddNewTaskContract.View

) : IAddNewTaskContract.Presenter {
    lateinit var token: String
    private lateinit var personalTodoApi: IPersonalTodoApi
    private lateinit var teamTodoApi: ITeamTodoApi
    override fun addPersonalTask(title: String, description: String) {
        personalTodoApi = PersonalTodoApi(token)
        teamTodoApi = TeamTodoApi(token)
        val singleTodoTask = SingleTodoTask(title, description)
        personalTodoApi.createPersonalTodo(singleTodoTask, ::onTaskSuccess, ::onTaskFailed)
    }

    override fun addTeamTask(title: String, description: String, assignee: String) {
        personalTodoApi = PersonalTodoApi(token)
        teamTodoApi = TeamTodoApi(token)
        val singleTodoTask = SingleTodoTask(title, description, assignee)
        teamTodoApi.createTeamTodo(singleTodoTask, ::onTaskSuccess, ::onTaskFailed)
    }

    private fun onTaskSuccess(successMessage: String) {
        view.showTaskAdded(successMessage)
    }

    private fun onTaskFailed(error: IOException) {
        view.showError(error)
    }

}