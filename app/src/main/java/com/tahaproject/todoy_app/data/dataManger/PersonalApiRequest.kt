package com.tahaproject.todoy_app.data.dataManger

import android.util.Log
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IPersonalTodoApi
import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.responses.PersonalTodo
import com.tahaproject.todoy_app.data.responses.PersonalTodoCreateResponse
import com.tahaproject.todoy_app.data.responses.PersonalTodoUpdateResponse
import com.tahaproject.todoy_app.util.EndPoint


class PersonalApiRequest(private val apiRequest:ApiRequest) : IPersonalTodoApi {

    override fun createPersonalTodo(personalTodoRequest:PersonalTodoRequest): PersonalTodoCreateResponse {
        val request = apiRequest.postRequest(personalTodoRequest, EndPoint.personalTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_PERSONAL_CREATE, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, PersonalTodoCreateResponse::class.java)
        }
    }

    override fun getPersonalTodos(): PersonalTodo {
        val request = apiRequest.getRequest(EndPoint.personalTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_PERSONAL_GET, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, PersonalTodo::class.java)
        }
    }

    override fun updatePersonalTodosStatus(personalTodoUpdateRequest:PersonalTodoUpdateRequest): PersonalTodoUpdateResponse {
        val request = apiRequest.putRequest(personalTodoUpdateRequest, EndPoint.personalTodo)
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            Log.i(ApiRequest.TAG_PERSONAL_UPDATE, "$jsonString")
            return apiRequest.gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java)
        }
    }
}