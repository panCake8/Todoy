package com.tahaproject.todoy_app.ui.presenter

import android.content.Context
import android.util.Log
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo


class HomePresenter(private val context: Context) :
    HomeContract.HomePresenter {
    private var view: HomeContract.HomeView? = null
    private val personalTodoApiImpl = PersonalTodoApi(context)
    private val teamTodoApi = TeamTodoApi(context)

    lateinit var personalData: List<Todo>
    lateinit var teamData: List<Todo>

    override fun fetchPersonalData() {
        view?.let { view ->
            personalTodoApiImpl.getPersonalTodos({
                personalData = it.value
                Log.i("personalData", personalData.toString())
            }, { ioException ->
                view.showError(ioException)
            }, this@HomePresenter)
        }
    }

    override fun fetchTeamData() {
        view?.let { view ->
            teamTodoApi.getTeamTodos({
                teamData = it.value
                Log.i("teamData", teamData.toString())

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