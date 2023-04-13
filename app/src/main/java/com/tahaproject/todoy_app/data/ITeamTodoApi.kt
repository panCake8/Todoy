package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*

interface ITeamTodoApi {
    fun createTeamTodo(
        teamTodoRequest: TeamToDoPostRequest,
        onSuccess: (TeamToDoResponse) -> Unit,
        onFailure: (Throwable) -> Unit)
    fun getTeamTodos( onSuccess: (TeamToDo) -> Unit,
    onFailure: (Throwable) -> Unit)
    fun updateTeamTodosStatus(
        teamTodoUpdateRequest: TeamToDoUpdateRequest,
        onSuccess: (TeamTodoUpdateResponse) -> Unit,
        onFailure: (Throwable) -> Unit)
}
