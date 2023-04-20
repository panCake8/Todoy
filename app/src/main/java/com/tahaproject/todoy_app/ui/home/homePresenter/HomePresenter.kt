package com.tahaproject.todoy_app.ui.home.homePresenter


import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException


class HomePresenter(private val view: HomeContract.IView) :
    HomeContract.IPresenter {
    lateinit var token: String
    private lateinit var personalTodoApiImpl: IPersonalTodoApi
    private lateinit var teamTodoApi: ITeamTodoApi


    override fun fetchPersonalData() {
        personalTodoApiImpl = PersonalTodoApi(token)
        teamTodoApi = TeamTodoApi(token)
        personalTodoApiImpl.getPersonalTodos(::onSuccessPersonalTodo, ::onFailed)
    }

    override fun fetchTeamData() {
        teamTodoApi.getTeamTodos(::onSuccessTeamTodo, ::onFailed)
    }

    private fun onSuccessPersonalTodo(toDosResponse: ToDosResponse) {
        view.showPersonalToDoData(toDosResponse)
    }

    private fun onSuccessTeamTodo(toDosResponse: ToDosResponse) {
        view.showTeamToDoData(toDosResponse)
    }

    private fun onFailed(ioException: IOException) {
        view.showError(ioException)
    }


}