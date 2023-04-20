package com.tahaproject.todoy_app.data.apiManger.teamTodo


import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.interceptors.AuthInterceptor
import com.tahaproject.todoy_app.data.interceptors.TodoInterceptor
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.ErrorMessage
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.net.UnknownHostException


class TeamTodoApi(token: String) : ApiRequest(), ITeamTodoApi {
    override val client =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(TodoInterceptor(token))
            .addInterceptor(logInterceptor)
            .build()

    override fun createTeamTodo(
        teamTodoRequest: SingleTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, teamTodoRequest.title)
            .add(Constants.Todo.DESCRIPTION, teamTodoRequest.description)
            .add(Constants.Todo.ASSIGNEE, teamTodoRequest.assignee)

            .build()
        val request = postRequest(formBody, Constants.EndPoints.teamTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string().let { jsonString ->
                        gson.fromJson(jsonString, SingleTodoTask::class.java)
                    }
                    onSuccess(Constants.ADDED)
                }
            }

        })

    }

    override fun getTeamTodos(
        onSuccess: (ToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val request = getRequest(Constants.EndPoints.teamTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (e is UnknownHostException)
                    onFailed(IOException(ErrorMessage.NO_INTERNET))
                else
                    onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string().let { jsonString ->
                        val teamTodosResponse = gson.fromJson(jsonString, ToDosResponse::class.java)
                        onSuccess(teamTodosResponse)
                    }
                } else if (response.code == PersonalTodoApi.UNAUTH)
                    onFailed(IOException(ErrorMessage.UNAUTHORIZED))
                else if (response.code in 500..600)
                    onFailed(IOException(ErrorMessage.SERVER_ERROR))
                else
                    onFailed(IOException(response.message))
            }

        })
    }


    override fun updateTeamTodosStatus(
        teamTodoUpdateRequest: UpdateTodoTask,
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
                if (response.isSuccessful) {
                    response.body?.string().let { jsonString ->
                        gson.fromJson(jsonString, UpdateTodoTask::class.java)
                    }
                    onSuccess(Constants.UPDATED)
                }
            }

        })

    }


}