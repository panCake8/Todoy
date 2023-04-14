package com.tahaproject.todoy_app.data.apiManger.teamTodo

import com.tahaproject.todoy_app.data.models.requests.TeamTodoRequest
import com.tahaproject.todoy_app.data.models.requests.TeamTodoUpdateRequest
import com.tahaproject.todoy_app.data.models.responses.TeamToDosResponse
import java.io.IOException

interface ITeamTodoApi {
    fun createTeamTodo(
        teamTodoRequest: TeamTodoRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getTeamTodos(
        onSuccess: (TeamToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun updateTeamTodosStatus(
        teamTodoUpdateRequest: TeamTodoUpdateRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
