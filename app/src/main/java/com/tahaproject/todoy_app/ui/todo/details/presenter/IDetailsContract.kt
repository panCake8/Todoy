package com.tahaproject.todoy_app.ui.todo.details.presenter

import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import java.io.IOException

interface IDetailsContract {
    interface IPresenter {
        fun updateTeamTodoTask(
            teamTodoUpdateRequest: UpdateTodoTask,
            onSuccess: (String) -> Unit,
            onFailed: (IOException) -> Unit
        )

        fun updatePersonalTodoTask(
            personalTodoUpdateRequest: UpdateTodoTask,
            onSuccess: (String) -> Unit,
            onFailed: (IOException) -> Unit
        )
    }
}
