package com.tahaproject.todoy_app.ui.todo.personal.presenter


import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.util.ErrorMessage
import java.io.IOException
import java.net.UnknownHostException

class PersonalTodoPresenter(private val view: PersonalTodoContract.IView) :
    PersonalTodoContract.IPresenter {
    lateinit var token: String
    private lateinit var personalTodoApi: IPersonalTodoApi
    override fun fetchData() {
        view.showLoading()
        personalTodoApi = PersonalTodoApi(token)
        personalTodoApi.getPersonalTodos(::showTodos, ::showError)
    }

    private fun showTodos(toDosResponse: ToDosResponse) {
        if (toDosResponse.value.isEmpty())
            view.showAnimation()
        else {
            view.showTodos(toDosResponse)
            view.hideLoading()
            view.hideAnimation()
        }
    }


    private fun showError(ioException: IOException) {
        when (ioException.message) {
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
        view.hideLoading()
    }

}