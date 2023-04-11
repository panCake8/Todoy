package com.tahaproject.todoy_app.data.dataManger

import android.util.Log

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.ITeamTodoApi
import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.EndPoint


class TeamApiRequest(private val apiRequest: ApiRequest) : ITeamTodoApi {

    override fun createTeamTodo(teamTodoRequest: TeamToDoPostRequest): TeamToDoResponse {
        val request = apiRequest.postRequest(teamTodoRequest, EndPoint.teamTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_TEAM_CREATE, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, TeamToDoResponse::class.java)
        }
    }

    override fun getTeamTodos(): TeamToDo {
        val request = apiRequest.getRequest(EndPoint.teamTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_TEAM_GET, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, TeamToDo::class.java)
        }
    }

    override fun updateTeamTodosStatus(teamTodoUpdateRequest: TeamToDoUpdateRequest): TeamTodoUpdateResponse {
        val request = apiRequest.putRequest(teamTodoUpdateRequest, EndPoint.teamTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_TEAM_UPDATE, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, TeamTodoUpdateResponse::class.java)
        }
    }
}