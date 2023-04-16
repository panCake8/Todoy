package com.tahaproject.todoy_app.ui.todo.personal.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.activities.presenter.HomePresenter
import java.io.IOException

class PersonalTodoPresenter(private val view: IPersonalTodoContract.IPersonalTodoView):
    IPersonalTodoContract.IPersonalTodoPresenter{
    private lateinit var context: Context
    private val personalTodoRequestImpl: IPersonalTodoApi = PersonalTodoApiImpl(context)


    override fun fetch(singleTodoTask: SingleTodoTask) {
        personalTodoRequestImpl.getPersonalTodos(::showData,::showError, HomePresenter(context))
    }



    private fun showData(toDosResponse: ToDosResponse) {
        view.showData(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}