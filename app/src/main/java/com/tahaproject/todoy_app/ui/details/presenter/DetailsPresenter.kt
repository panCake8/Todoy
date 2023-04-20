package com.tahaproject.todoy_app.ui.details.presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.util.ErrorMessage
import java.io.IOException
import java.net.UnknownHostException

class DetailsPresenter(private val view: DetailsContract.IView) : DetailsContract.IPresenter {
    private lateinit var personalTodoApi: IPersonalTodoApi
    private lateinit var teamTodoApi: ITeamTodoApi
    override fun updateTeamTodoTask(
        teamTodoUpdateRequest: UpdateTodoTask
    ) {
        teamTodoApi.updateTeamTodosStatus(
            teamTodoUpdateRequest,
            ::onTaskSuccess,
            ::onTaskFailed
        )
    }

    fun initApis(token: String) {
        personalTodoApi = PersonalTodoApi(token)
        teamTodoApi = TeamTodoApi(token)
    }

    override fun updatePersonalTodoTask(
        personalTodoUpdateRequest: UpdateTodoTask
    ) {
        personalTodoApi.updatePersonalTodosStatus(
            personalTodoUpdateRequest,
            ::onTaskSuccess,
            ::onTaskFailed
        )

    }

    private fun onTaskSuccess(successMessage: String) {
        view.showTaskUpdated(successMessage)
    }

    private fun onTaskFailed(error: IOException) {
        when (error.message) {
            UnknownHostException(ErrorMessage.NO_INTERNET).message -> {
                view.noInternet()
                view.showError(error)
            }

            IOException(ErrorMessage.SERVER_ERROR).message -> {
                view.serverError()
                view.showError(error)
            }

            else -> view.showError(error)
        }
    }
}

