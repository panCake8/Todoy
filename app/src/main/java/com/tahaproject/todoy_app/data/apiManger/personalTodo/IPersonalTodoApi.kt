package com.tahaproject.todoy_app.data.apiManger.personalTodo

import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.domain.requests.PersonalTodoPostRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodoUpdateResponse
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import java.io.IOException

interface IPersonalTodoApi {
    fun createPersonalTodo(
        personalTodoPostRequest: PersonalTodoPostRequest, onSuccess: (PersonalTodoPostRequest) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getPersonalTodos(
        onSuccess: (PersonalTodosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (PersonalTodoUpdateResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
