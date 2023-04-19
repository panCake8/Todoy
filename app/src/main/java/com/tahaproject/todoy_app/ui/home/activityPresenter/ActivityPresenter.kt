package com.tahaproject.todoy_app.ui.home.activityPresenter


import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.util.ErrorMessage
import java.io.IOException
import java.net.UnknownHostException


class ActivityPresenter(private val view: ActivityContract.IView) :
    ActivityContract.IPresenter {
    lateinit var token: String
    private lateinit var personalTodoApiImpl: IPersonalTodoApi
    override fun fetchPersonalData() {
        personalTodoApiImpl = PersonalTodoApi(token)
        personalTodoApiImpl.getPersonalTodos(::onSuccess, ::onFailed)
    }

    private fun onSuccess(toDosResponse: ToDosResponse) {
        view.showPersonalToDoData(toDosResponse)
        onHome()
    }

    private fun onFailed(ioException: IOException) {
        when (ioException.message) {
            IOException(ErrorMessage.UNAUTHORIZED).message -> {
                onLogin()
                view.showError(ioException)
            }

            UnknownHostException(ErrorMessage.NO_INTERNET).message -> {
                view.noInternet()
                view.showError(ioException)
            }

            IOException(ErrorMessage.SERVER_ERROR).message -> {
                view.serverError()
                view.showError(ioException)
            }

            else -> view.showError(ioException)
        }
    }

    override fun onHome() {
        view.navigateToHomeScreen()
    }

    override fun onLogin() {
        view.navigateToLoginScreen()
    }

}