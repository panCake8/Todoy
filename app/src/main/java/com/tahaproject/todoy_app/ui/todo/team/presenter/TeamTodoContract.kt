package com.tahaproject.todoy_app.ui.todo.team.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApiImpl
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.activities.presenter.HomePresenter
import com.tahaproject.todoy_app.ui.todo.personal.presenter.IPersonalTodoContract
import java.io.IOException

class TeamTodoContract(private val view: IPersonalTodoContract.IPersonalTodoView):
    IPersonalTodoContract.IPersonalTodoPresenter{
    private lateinit var context: Context
    private lateinit var homePresenter: HomePresenter
    private val teamTodoRequestImpl: ITeamTodoApi = TeamTodoApiImpl(context)


    override fun fetch(singleTodoTask: SingleTodoTask) {
        teamTodoRequestImpl.getTeamTodos(::showData,::showError, homePresenter)
    }



    private fun showData(toDosResponse: ToDosResponse) {
        view.showData(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}