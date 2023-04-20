package com.tahaproject.todoy_app.ui.todo.team.presenter

import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

class TeamTodoPresenter(private val view: ITeamTodoContract.IView, token: String):
    ITeamTodoContract.IPresenter{

    private val teamTodoRequestImpl: ITeamTodoApi = TeamTodoApi(token)


    override fun fetchData() {
        teamTodoRequestImpl.getTeamTodos(::showData,::showError)
    }



    private fun showData(toDosResponse: ToDosResponse) {
        view.showTodos(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}