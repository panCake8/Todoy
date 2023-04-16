package com.tahaproject.todoy_app.ui.todo.team.presenter

import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

interface ITeamTodoContract {

    interface  ITeamTodoView{

        fun showData(toDosResponse: ToDosResponse)

        fun showError(error: IOException)
    }


    interface ITeamTodoPresenter{

        fun fetch(singleTodoTask: SingleTodoTask)
    }
}