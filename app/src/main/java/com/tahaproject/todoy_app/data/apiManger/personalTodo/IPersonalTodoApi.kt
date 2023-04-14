package com.tahaproject.todoy_app.data.apiManger.personalTodo

import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import java.io.IOException

interface IPersonalTodoApi {
    fun createPersonalTodo(
        personalTodoRequest: PersonalTodoRequest, onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getPersonalTodos(
        onSuccess: (PersonalTodosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )
}