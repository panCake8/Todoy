package com.tahaproject.todoy_app.data.apiManger

import android.util.Log
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.IPersonalTodoApi
import com.tahaproject.todoy_app.data.requests.PersonalTodoRequest
import com.tahaproject.todoy_app.data.requests.PersonalTodoUpdateRequest
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Response
import java.io.IOException


class PersonalApiRequest(private val apiRequest: ApiRequest) : IPersonalTodoApi {

    override fun createPersonalTodo(personalTodoRequest: PersonalTodoRequest) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, personalTodoRequest.title)
            .add(Constants.Todo.DESCRIPTION, personalTodoRequest.description)
            .build()
        val request = apiRequest.postRequest(formBody, Constants.EndPoints.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, PersonalTodoCreateResponse::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }

    override fun getPersonalTodos() {
        val request = apiRequest.getRequest(Constants.EndPoints.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    apiRequest.gson.fromJson(jsonString, PersonalTodo::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }

    override fun updatePersonalTodosStatus(personalTodoUpdateRequest: PersonalTodoUpdateRequest) {
        val formBody = FormBody.Builder().add(Constants.Todo.ID, personalTodoUpdateRequest.id)
            .add(Constants.Todo.STATUS, personalTodoUpdateRequest.status.toString())
            .build()
        val request =
            apiRequest.putRequest(formBody, Constants.EndPoints.personalTodo)
        apiRequest.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.i("TAG", it) }
            }

            override fun onResponse(call: Call, response: Response) {
//                response.body?.string().let { jsonString ->
//                    Log.i(ApiRequest.TAG_PERSONAL_UPDATE, "$jsonString")
//                    apiRequest.gson.fromJson(jsonString, PersonalTodoUpdateResponse::class.java)
//                }
                Log.i("TAG", response.code.toString())
            }

        })

    }
}