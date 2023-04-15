package com.tahaproject.todoy_app.ui.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.responses.PersonalTodosResponse
import java.io.IOException


class HomePresenter(private val view: IHomeContract.IView, context: Context) :
    IHomeContract.IPresenter {
    private val personalTodoApi: IPersonalTodoApi = PersonalTodoApi(context)
    override fun fetchData() {
        personalTodoApi.getPersonalTodos(::showData, ::showError, this)
    }


    private fun showData(personalTodosResponse: PersonalTodosResponse) {
//        view.showData(personalTodosResponse)
    }


    private fun showError(ioException: IOException) {
        view.showError(ioException)
    }


    override fun onUnauthorizedError() {
        view.navigateToLoginScreen()
    }


    override fun onHome() {
        view.navigateToHomeScreen()
    }

}