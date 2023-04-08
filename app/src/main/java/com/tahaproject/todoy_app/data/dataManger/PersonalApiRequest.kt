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

    override fun createPersonalTodo(): PersonalTodoCreateResponse {
        val personalTodoRequest = PersonalTodoRequest("", "")
        val request = apiRequest.postRequest(personalTodoRequest, EndPoint.personalTodo)
        lateinit var result: PersonalTodoCreateResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, PersonalTodoCreateResponse::class.java)
            Log.i(ApiRequest.TAG_PERSONAL_CREATE, "$result")
        }
        return result
    }

    override fun getPersonalTodos(): PersonalTodo {
        val request = apiRequest.getRequest("", EndPoint.personalTodo)
        lateinit var result: PersonalTodo
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, PersonalTodo::class.java)
            Log.i(ApiRequest.TAG_PERSONAL_GET, "$result")
        }
        return result

    }

    override fun updatePersonalTodosStatus(): PersonalTodoUpdateResponse {
        val personalTodoUpdateRequest = PersonalTodoUpdateRequest("", 0)
        val request = apiRequest.putRequest(personalTodoUpdateRequest, EndPoint.personalTodo)
        lateinit var result: PersonalTodoUpdateResponse
        val response = apiRequest.client.newCall(request).execute()
        response.body?.string().let { jsonString ->
            result = apiRequest.gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java)
            Log.i(ApiRequest.TAG_PERSONAL_UPDATE, "$result")
        }
        return result
    }
}