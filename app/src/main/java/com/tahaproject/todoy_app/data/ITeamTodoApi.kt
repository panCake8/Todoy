package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.*

interface ITeamTodoApi {
    fun createTeamTodo(): TeamToDoResponse
    fun getTeamTodos(): TeamToDo
    fun updateTeamTodosStatus(): TeamTodoUpdateResponse
}
