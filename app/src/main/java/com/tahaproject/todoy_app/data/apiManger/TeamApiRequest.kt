package com.tahaproject.todoy_app.data.apiManger


import android.util.Log
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.ITeamTodoApi
import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Response
import java.io.IOException


class TeamApiRequest(private val apiRequest: ApiRequest) : ITeamTodoApi {

    override fun createTeamTodo(teamTodoRequest: TeamToDoPostRequest) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, teamTodoRequest.title)
            .add(Constants.Todo.ASSIGNEE, teamTodoRequest.assignee)
            .add(Constants.Todo.DESCRIPTION, teamTodoRequest.description)
            .build()
        val request = apiRequest.postRequest(formBody, Constants.EndPoints.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, TeamToDoResponse::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }

    override fun getTeamTodos() {
        val request = apiRequest.getRequest(Constants.EndPoints.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, TeamToDo::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })
    }


    override fun updateTeamTodosStatus(teamTodoUpdateRequest: TeamToDoUpdateRequest) {
        val formBody = FormBody.Builder().add(Constants.Todo.ID, teamTodoUpdateRequest.id)
            .add(Constants.Todo.STATUS, teamTodoUpdateRequest.status.toString())
            .build()
        val request = apiRequest.putRequest(formBody, Constants.EndPoints.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, TeamTodoUpdateResponse::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }
}