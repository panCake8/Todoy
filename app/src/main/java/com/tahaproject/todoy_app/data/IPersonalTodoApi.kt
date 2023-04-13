package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*

interface IPersonalTodoApi {
    fun createPersonalTodo(personalTodoRequest: PersonalTodoRequest)
    fun getPersonalTodos()
    fun updatePersonalTodosStatus(personalTodoUpdateRequest: PersonalTodoUpdateRequest)
}
