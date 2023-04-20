package com.tahaproject.todoy_app.ui.todo.personal.presenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

interface PersonalTodoContract {
    interface IView {
        fun showTodos(toDosResponse: ToDosResponse)
        fun showError(error: IOException)
        fun showLoading()
        fun hideLoading()
    }

    interface IPresenter {
        fun fetchData()
    }
}