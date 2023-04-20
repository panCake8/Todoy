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
    private lateinit var personalTodoApi: IPersonalTodoApi
    private lateinit var teamTodoApi: ITeamTodoApi
    override fun addPersonalTask(title: String, description: String) {
        if (isValid(title, description, null)) {
            val singleTodoTask = SingleTodoTask(title, description)
            personalTodoApi.createPersonalTodo(singleTodoTask, ::onTaskSuccess, ::onTaskFailed)
        }
    }

    override fun addTeamTask(title: String, description: String, assignee: String?) {
        if (isValid(title, description, assignee)) {
            val singleTodoTask = assignee?.let { SingleTodoTask(title, description, it) }
            singleTodoTask?.let { teamTodoApi.createTeamTodo(it, ::onTaskSuccess, ::onTaskFailed) }
        }
    }

    fun initApis(token: String) {
        personalTodoApi = PersonalTodoApi(token)
        teamTodoApi = TeamTodoApi(token)
    }

    override fun isValid(title: String, description: String, assignee: String?): Boolean {
        return if (!isValidTitle(title)) {
            view.showInvalidTitleMassage("please enter title")
            false
        } else if (!isValidDescription(description)) {
            view.showInvalidDescriptionMassage("please enter description")
            false
        } else if (isValidAssignee(assignee) == false) {
            view.showInvalidAssigneeMassage("please enter assignee name")
            false
        } else {
            true
        }
    }

    private fun isValidTitle(title: String) = title.isNotEmpty()
    private fun isValidDescription(description: String) = description.isNotEmpty()

    private fun isValidAssignee(assignee: String?) = assignee?.isNotEmpty()

    private fun onTaskSuccess(successMessage: String) {
        view.showTaskAdded(successMessage)
    }

    private fun onTaskFailed(error: IOException) {
        view.showError(error)
    }

}