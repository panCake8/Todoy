package com.tahaproject.todoy_app.ui.presenter

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import java.io.IOException

class HomePresenter(private val view: IHomeContract.IView, context: Context) : IHomeContract.IPresenter {

    private val personalTodoApiImpl = PersonalTodoApi(context)
    private val teamTodoApi = TeamTodoApi(context)

    lateinit var personalData1: List<Todo>
    lateinit var teamData1: List<Todo>

    override fun fetchPersonalData() {
        view.let {
            personalTodoApiImpl.getPersonalTodos(::showPersonalData, ::showError, this@HomePresenter)
        }


    }

    override fun fetchTeamData() {
        view.let {
            teamTodoApi.getTeamTodos(::showTeamData, ::showError, this@HomePresenter)
        }
    }

    private fun showError(ioException: IOException) {
        view.showError(ioException)
    }

    private fun showPersonalData(personalData: ToDosResponse) {
        view.showPersonalData(personalData)
    }

    private fun showTeamData(teamData: ToDosResponse) {
        view.showTeamData(teamData)
    }

    override fun onUnauthorizedError() {
        view.navigateToLoginScreen()
    }

}