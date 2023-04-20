package com.tahaproject.todoy_app.ui.todo.team.presenter

import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

class TeamTodoPresenter(private val view: TeamTodoContract.IView) :
    TeamTodoContract.IPresenter {
    lateinit var token: String
    private lateinit var teamTodoApi: ITeamTodoApi
    override fun fetchData() {
        view.showLoading()
        teamTodoApi = TeamTodoApi(token)
        teamTodoApi.getTeamTodos(::showData, ::showError)
    }

    private fun showData(toDosResponse: ToDosResponse) {
        view.showTodos(toDosResponse)
        view.hideLoading()
    }

    private fun showError(ioException: IOException) {
        view.showError(ioException)
        view.hideLoading()
    }

}