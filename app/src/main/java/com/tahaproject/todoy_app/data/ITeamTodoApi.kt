package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*

interface ITeamTodoApi {
    fun createTeamTodo(teamTodoRequest: TeamToDoPostRequest)
    fun getTeamTodos()
    fun updateTeamTodosStatus(teamTodoUpdateRequest: TeamToDoUpdateRequest)
}
