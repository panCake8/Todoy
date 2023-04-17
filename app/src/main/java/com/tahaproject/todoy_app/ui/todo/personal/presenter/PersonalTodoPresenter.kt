package com.tahaproject.todoy_app.ui.todo.personal.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.activities.presenter.HomePresenter
import java.io.IOException

class PersonalTodoPresenter(private val view: IPersonalTodoContract.IView):
    IPersonalTodoContract.IPresenter{
    private lateinit var context: Context
    private lateinit var homePresenter: HomePresenter
    private val personalTodoRequestImpl: IPersonalTodoApi = PersonalTodoApiImpl(context)


    override fun fetchData(singleTodoTask: SingleTodoTask) {
        personalTodoRequestImpl.getPersonalTodos(::showTodos,::showError, homePresenter)
    }



    private fun showTodos(toDosResponse: ToDosResponse) {
        view.showTodos(toDosResponse)
    }



    private fun showError(ioException: IOException){
        view.showError(ioException)
    }

}