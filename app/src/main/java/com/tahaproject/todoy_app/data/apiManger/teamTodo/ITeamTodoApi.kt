package com.tahaproject.todoy_app.data.apiManger.teamTodo

import com.tahaproject.todoy_app.data.domain.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.domain.responses.TeamToDosResponse
import com.tahaproject.todoy_app.data.domain.responses.TeamTodoUpdateResponse
import java.io.IOException

interface ITeamTodoApi {
    fun createTeamTodo(
        teamTodoRequest: TeamToDoPostRequest,
        onSuccess: (TeamToDoPostRequest) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getTeamTodos(
        onSuccess: (TeamToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun updateTeamTodosStatus(
        teamTodoUpdateRequest: TeamToDoUpdateRequest,
        onSuccess: (TeamTodoUpdateResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
