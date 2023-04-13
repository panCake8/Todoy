package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*

interface IPersonalTodoApi {
    fun createPersonalTodo(
        personalTodoRequest: PersonalTodoRequest,
        onSuccess: (PersonalTodoCreateResponse) -> Unit,
        onFailure: (Throwable) -> Unit)
    fun getPersonalTodos(
    onSuccess: (PersonalTodo) -> Unit,
    onFailure: (Throwable) -> Unit)
    fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (PersonalTodoUpdateResponse) -> Unit,
        onFailure: (Throwable) -> Unit)
}
