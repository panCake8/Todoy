package com.tahaproject.todoy_app.ui.home.homePresenter


import android.util.Log
import com.tahaproject.todoy_app.data.apiManger.personalTodo.IPersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.ITeamTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse


class HomePresenter(private val view: HomeContract.IView) :
    HomeContract.IPresenter {
     lateinit var token: String
    private val personalTodoApiImpl: IPersonalTodoApi = PersonalTodoApi(token)
    private val teamTodoApi: ITeamTodoApi = TeamTodoApi(token)

    lateinit var personalData: ToDosResponse
    lateinit var teamData: ToDosResponse

    override fun fetchPersonalData() {
        view.let { view ->
            personalTodoApiImpl.getPersonalTodos({
                personalData = it
                Log.i("personalData", personalData.toString())
            }, { ioException ->
                view.showError(ioException)
            })
        }
    }

    override fun fetchTeamData() {
        view.let { view ->
            teamTodoApi.getTeamTodos({
                teamData = it
                Log.i("teamData", teamData.toString())

            }, { ioException ->
                view.showError(ioException)
            })


        }
    }


    override fun onUnauthorizedError() {
        view.navigateToLoginScreen()
    }

    override fun onHome() {
        view.navigateToHomeScreen()
    }

}