package com.tahaproject.todoy_app.data.apiManger.personalTodo

import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.presenter.HomePresenter
import java.io.IOException

interface IPersonalTodoApi {
    fun createPersonalTodo(
        personalTodoRequest: SingleTodoTask, onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getPersonalTodos(
        onSuccess: (ToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit, presenter: HomePresenter
    )

    fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: UpdateTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
