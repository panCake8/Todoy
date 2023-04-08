package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*

interface ITeamTodoApi {
    fun createTeamTodo(teamTodoRequest: TeamToDoPostRequest): TeamToDoResponse
    fun getTeamTodos(): TeamToDo
    fun updateTeamTodosStatus(teamTodoUpdateRequest: TeamToDoUpdateRequest): TeamTodoUpdateResponse
}
