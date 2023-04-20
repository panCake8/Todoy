package com.tahaproject.todoy_app.data.apiManger.teamTodo

import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

interface ITeamTodoApi {
    fun createTeamTodo(
        teamTodoRequest: SingleTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun getTeamTodos(
        onSuccess: (ToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    )

    fun updateTeamTodosStatus(
        teamTodoUpdateRequest: UpdateTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    )
}
