package com.tahaproject.todoy_app.data.apiManger.teamTodo


import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamTodoRequest
import com.tahaproject.todoy_app.data.domain.requests.TeamTodoUpdateRequest
import com.tahaproject.todoy_app.data.domain.responses.TeamToDosResponse
import com.tahaproject.todoy_app.data.domain.responses.TeamTodoUpdateResponse
import com.tahaproject.todoy_app.data.interceptors.TodoInterceptor
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class TeamTodoApiImpl : ApiRequest(), ITeamTodoApi {
    private val client =
        OkHttpClient.Builder().addInterceptor(TodoInterceptor()).addInterceptor(logInterceptor)
            .build()

    override fun createTeamTodo(
        teamTodoRequest: TeamTodoRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, teamTodoRequest.value.title)
            .add(Constants.Todo.DESCRIPTION, teamTodoRequest.value.description)
            .add(Constants.Todo.ASSIGNEE, teamTodoRequest.value.assignee)

            .build()
        val request = postRequest(formBody, Constants.EndPoints.teamTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    gson.fromJson(jsonString, TeamTodoRequest::class.java)
                }
                onSuccess(Constants.ADDED)
            }

        })

    }

    override fun getTeamTodos(
        onSuccess: (TeamToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val request = getRequest(Constants.EndPoints.teamTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val teamTodosResponse = gson.fromJson(jsonString, TeamToDosResponse::class.java)
                    onSuccess(teamTodosResponse)
                }
            }

        })
    }


    override fun updateTeamTodosStatus(
        teamTodoUpdateRequest: TeamTodoUpdateRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.ID, teamTodoUpdateRequest.id)
            .add(Constants.Todo.STATUS, teamTodoUpdateRequest.status.toString())
            .build()
        val request = putRequest(formBody, Constants.EndPoints.teamTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    gson.fromJson(jsonString, TeamTodoUpdateResponse::class.java)
                }
                onSuccess(Constants.UPDATED)

            }

        })

    }


}