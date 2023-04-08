package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.*

interface IPersonalTodoApi {
    fun createPersonalTodo(): PersonalTodoCreateResponse
    fun getPersonalTodos(): PersonalTodo
    fun updatePersonalTodosStatus(): PersonalTodoUpdateResponse
}
