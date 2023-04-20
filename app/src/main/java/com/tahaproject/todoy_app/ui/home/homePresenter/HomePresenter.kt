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
        view.showLoading()
        personalTodoApiImpl = PersonalTodoApi(token)
        personalTodoApiImpl.getPersonalTodos(::onSuccessPersonalTodo, ::onFailed)
    }

    override fun fetchTeamData() {
        view.showLoading()
        teamTodoApi = TeamTodoApi(token)
        teamTodoApi.getTeamTodos(::onSuccessTeamTodo, ::onFailed)
    }

    private fun onSuccessPersonalTodo(toDosResponse: ToDosResponse) {
        if (toDosResponse.value.isEmpty())
            view.showAnimation()
        else {
            view.showPersonalToDoData(toDosResponse)
            view.hideLoading()
            view.hideAnimation()
        }
    }

    private fun onSuccessTeamTodo(toDosResponse: ToDosResponse) {
        view.showTeamToDoData(toDosResponse)
        view.showChart()
        view.hideLoading()
    }

    private fun onFailed(ioException: IOException) {
        view.showError(ioException)
        view.hideLoading()
    }


}