package com.tahaproject.todoy_app.ui.home.homePresenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import java.io.IOException


class HomePresenter(private val view: IHomeContract.IView, token: String) : IHomeContract.IPresenter {

    private val personalTodoApiImpl: IPersonalTodoApi = PersonalTodoApi(token)
    private val teamTodoApi: ITeamTodoApi = TeamTodoApi(token)

    override fun fetchPersonalData() {
        personalTodoApiImpl.getPersonalTodos( ::onPersonalSuccess, ::onFail )
    }

    override fun fetchTeamData() {
        teamTodoApi.getTeamTodos( ::onTeamSuccess, ::onFail )
    }

    private fun onTeamSuccess(todo : ToDosResponse) {
        view.showTeamToDoData(todo)
    }

    private fun onPersonalSuccess(todo : ToDosResponse) {
        view.showPersonalToDoData(todo)
    }

    private fun onFail(error: IOException) {
        view.showError(error)
    }

    override fun onUnauthorizedError() {
        view.navigateToLoginScreen()
    }

    override fun onHome() {
        view.navigateToHomeScreen()
    }

}