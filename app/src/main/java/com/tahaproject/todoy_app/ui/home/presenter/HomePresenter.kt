package com.tahaproject.todoy_app.ui.home.presenter

import com.tahaproject.todoy_app.data.FakeDataManager
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.domain.responses.TeamToDosResponse
import com.tahaproject.todoy_app.util.Constants


class HomePresenter :
    HomeContract.HomePresenter {
    private var view: HomeContract.HomeView? = null
    private val personalTodoApiImpl = PersonalTodoApiImpl()
    override fun fetchData() {
        view?.let { view ->
            personalTodoApiImpl.getPersonalTodos({ personalResponse ->
                view.showData(personalResponse)
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

}