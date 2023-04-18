package com.tahaproject.todoy_app.ui.todo.details.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import java.io.IOException

class IDetailsPresenter(token: String) : IDetailsContract.IPresenter {
    private val personalTodoApiImpl: IPersonalTodoApi = PersonalTodoApi(token)
    private val teamTodoApi: ITeamTodoApi = TeamTodoApi(token)
    override fun updateTeamTodoTask(
        teamTodoUpdateRequest: UpdateTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        teamTodoApi
            .updateTeamTodosStatus(
                teamTodoUpdateRequest, onSuccess, onFailed
            )
    }

    override fun updatePersonalTodoTask(
        personalTodoUpdateRequest: UpdateTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        personalTodoApiImpl
            .updatePersonalTodosStatus(
                personalTodoUpdateRequest, onSuccess, onFailed
            )
    }


}

