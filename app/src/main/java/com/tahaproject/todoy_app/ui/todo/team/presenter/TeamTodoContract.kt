package com.tahaproject.todoy_app.ui.todo.team.presenter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

interface TeamTodoContract {
    interface IView {
        fun showTodos(toDosResponse: ToDosResponse)
        fun showError(error: IOException)
        fun showLoading()
        fun hideLoading()
        fun showAnimation()
        fun hideAnimation()
    }

    interface IPresenter {
        fun fetchData()
    }
}