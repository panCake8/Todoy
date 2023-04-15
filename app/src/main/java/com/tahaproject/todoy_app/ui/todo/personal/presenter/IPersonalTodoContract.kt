package com.tahaproject.todoy_app.ui.todo.personal.presenter

import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

interface IPersonalTodoContract {

    interface  IPersonalTodoView{

        fun showData(toDosResponse: ToDosResponse)

        fun showError(error: IOException)
    }


    interface IPersonalTodoPresenter{

        fun fetch(singleTodoTask: SingleTodoTask)
    }
}