package com.tahaproject.todoy_app.data.apiManger.personalTodo

import com.tahaproject.todoy_app.data.models.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.models.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.activities.presenter.HomePresenter
import java.io.IOException

interface IPersonalTodoApi {
    fun createPersonalTodo(
        personalTodoRequest: PersonalTodoRequest, onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getPersonalTodos(
        onSuccess: (ToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit, presenter: HomePresenter
    )

    fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
