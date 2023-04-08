package com.tahaproject.todoy_app.data.dataManger

import android.util.Log

import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.ITeamTodoApi
import com.tahaproject.todoy_app.data.requests.TeamToDoPostRequest
import com.tahaproject.todoy_app.data.requests.TeamToDoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.EndPoint


class TeamApi(private val apiRequest: ApiRequest) : ITeamTodoApi {

    override fun createTeamTodo():TeamToDoResponse {
        val teamTodoRequest = TeamToDoPostRequest("", "","")
        val request = apiRequest.postRequest(teamTodoRequest, EndPoint.teamTodo)
        lateinit var result: TeamToDoResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, TeamToDoResponse::class.java)
            Log.i(ApiRequest.TAG_TEAM_CREATE, "$result")
        }
        return result

    }

    override fun getTeamTodos() :TeamToDo{

        val request = apiRequest.getRequest("", EndPoint.teamTodo)
        lateinit var result: TeamToDo
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, TeamToDo::class.java)
            Log.i(ApiRequest.TAG_TEAM_GET, "$result")
        }
        return result
    }

    override fun updateTeamTodosStatus():TeamTodoUpdateResponse {
        val teamTodoUpdateRequest = TeamToDoUpdateRequest("", 0)
        val request = apiRequest.putRequest(teamTodoUpdateRequest, EndPoint.teamTodo)
        lateinit var result: TeamTodoUpdateResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, TeamTodoUpdateResponse::class.java)
            Log.i(ApiRequest.TAG_TEAM_UPDATE, "$result")
        }
        return result
    }
}