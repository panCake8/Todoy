package com.tahaproject.todoy_app.data.dataManger

import android.util.Log
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IPersonalTodoApi
import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.data.responses.*
import com.tahaproject.todoy_app.util.EndPoint
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class PersonalApiRequest(private val apiRequest: ApiRequest) : IPersonalTodoApi {

    override fun createPersonalTodo(
        personalTodoRequest: PersonalTodoRequest,
        onSuccess: (PersonalTodoCreateResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val request = apiRequest.postRequest(personalTodoRequest, EndPoint.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, PersonalTodoCreateResponse::class.java))
                    Log.i(ApiRequest.TAG_PERSONAL_CREATE, "$jsonString")
                }
            }
        })

    }

    override fun getPersonalTodos(
        onSuccess: (PersonalTodo) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val request = apiRequest.getRequest(EndPoint.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    onSuccess(apiRequest.gson.fromJson(jsonString, PersonalTodo::class.java))
                    Log.i(ApiRequest.TAG_PERSONAL_GET, "$jsonString")
                }
            }
        })

    }
    override fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: PersonalTodoUpdateRequest,
        onSuccess: (PersonalTodoUpdateResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val request = apiRequest.putRequest(personalTodoUpdateRequest, EndPoint.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->

                    onSuccess( apiRequest.gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java))
                    Log.i(ApiRequest.TAG_PERSONAL_UPDATE, "$jsonString")
                }
            }
        })
    }
}