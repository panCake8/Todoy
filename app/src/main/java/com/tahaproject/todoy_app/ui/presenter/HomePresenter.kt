package com.tahaproject.todoy_app.ui.presenter

import android.content.Context
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl


class HomePresenter(context: Context) :
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