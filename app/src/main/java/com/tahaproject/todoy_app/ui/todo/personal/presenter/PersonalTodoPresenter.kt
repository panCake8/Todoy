package com.tahaproject.todoy_app.ui.todo.personal.presenter

import android.content.Context
import android.util.JsonToken
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import java.io.IOException

class PersonalTodoPresenter(private val view: IPersonalTodoContract.IView):
    IPersonalTodoContract.IPresenter{
     private lateinit var token: String
    private lateinit var context: Context

    private val personalTodoRequestImpl: IPersonalTodoApi = PersonalTodoApi(token)


    override fun fetchData() {
        personalTodoRequestImpl.getPersonalTodos(::showTodos,::showError)
    }



    private fun showTodos(toDosResponse: ToDosResponse) {
        view.showTodos(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}