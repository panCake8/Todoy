package com.tahaproject.todoy_app.data.dataManger

import android.util.Log

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.ITeamTodoApi
import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.EndPoint
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class TeamApiRequest(private val apiRequest: ApiRequest) : ITeamTodoApi {

    override fun createTeamTodo(
        teamTodoRequest: TeamToDoPostRequest,
        onSuccess: (TeamToDoResponse) -> Unit,
        onFailure: (Throwable) -> Unit) {
        val request = apiRequest.postRequest(teamTodoRequest, EndPoint.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, TeamToDoResponse::class.java))
                    Log.i(ApiRequest.TAG_TEAM_CREATE, "$jsonString")
                }
            }
        })
    }

    override fun getTeamTodos(onSuccess: (TeamToDo) -> Unit,
                              onFailure: (Throwable) -> Unit) {
        val request = apiRequest.getRequest(EndPoint.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, TeamToDo::class.java))
                    Log.i(ApiRequest.TAG_TEAM_GET, "$jsonString")
                }
            }
        })
    }

    override fun updateTeamTodosStatus(
        teamTodoUpdateRequest: TeamToDoUpdateRequest,
        onSuccess: (TeamTodoUpdateResponse) -> Unit,
        onFailure: (Throwable) -> Unit) {
        val request = apiRequest.putRequest(teamTodoUpdateRequest, EndPoint.teamTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, TeamTodoUpdateResponse::class.java))
                    Log.i(ApiRequest.TAG_TEAM_UPDATE, "$jsonString")
                }
            }
        })
    }
}