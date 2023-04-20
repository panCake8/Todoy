package com.tahaproject.todoy_app.ui.todo.personal.presenter


import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

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
        view.showTodos(toDosResponse)
        view.hideLoading()
    }


    private fun showError(ioException: IOException) {
        view.showError(ioException)
        view.hideLoading()
    }

}