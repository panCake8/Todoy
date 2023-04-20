package com.tahaproject.todoy_app.ui.details.presenter

import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import okio.IOException

interface DetailsContract {
    interface IView {
        fun showTaskUpdated(successMessage: String)
        fun showError(error: IOException)
        fun noInternet()
        fun serverError()
    }

    interface IPresenter {
        fun updateTeamTodoTask(teamTodoUpdateRequest: UpdateTodoTask)

        fun updatePersonalTodoTask(personalTodoUpdateRequest: UpdateTodoTask)
    }
}
