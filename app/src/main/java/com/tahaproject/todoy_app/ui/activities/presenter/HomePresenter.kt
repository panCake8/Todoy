package com.tahaproject.todoy_app.ui.activities.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.FakeDataManager
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.domain.responses.TeamToDosResponse
import com.tahaproject.todoy_app.util.Constants


class HomePresenter(private val context: Context) :
    HomeContract.HomePresenter {
    private var view: HomeContract.HomeView? = null
    private val personalTodoApiImpl = PersonalTodoApiImpl(context)
    override fun fetchData() {
        view?.let { view ->
            personalTodoApiImpl.getPersonalTodos({
            }, { ioException ->
                view.showError(ioException)
            }, this@HomePresenter)
        }
    }

    override fun attach(homeView: HomeContract.HomeView) {
        this.view = homeView
    }

    override fun deAttach() {
        view = null
    }

    override fun onUnauthorizedError() {
        view?.navigateToLoginScreen()
    }

    override fun onHome() {
        view?.navigateToHomeScreen()
    }

}