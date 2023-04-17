package com.tahaproject.todoy_app.ui.home.presenter


import android.util.Log
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo


class HomePresenter(private val view: HomeContract.HomeView, token: String) :
    HomeContract.HomePresenter {
    private val personalTodoApiImpl: IPersonalTodoApi = PersonalTodoApi(token)
    private val teamTodoApi: ITeamTodoApi = TeamTodoApi(token)

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




    override fun onUnauthorizedError() {
        view?.navigateToLoginScreen()
    }

    override fun onHome() {
        view?.navigateToHomeScreen()
    }

}