package com.tahaproject.todoy_app.data.apiManger.personalTodo

import android.content.Context
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.interceptors.AuthInterceptor
import com.tahaproject.todoy_app.data.interceptors.TodoInterceptor
import com.tahaproject.todoy_app.data.interceptors.UnAuthorizedException
import com.tahaproject.todoy_app.data.models.requests.SingleTodoTask
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.ui.presenter.HomePresenter
import com.tahaproject.todoy_app.util.Constants
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class PersonalTodoApi( context: Context) : ApiRequest(), IPersonalTodoApi {
    private val client =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(TodoInterceptor(context))
            .addInterceptor(logInterceptor)
            .build()

    override fun createPersonalTodo(
        personalTodoRequest: SingleTodoTask, onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.TITLE, personalTodoRequest.title)
            .add(Constants.Todo.DESCRIPTION, personalTodoRequest.description)
            .build()
        val request = postRequest(formBody, Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    gson.fromJson(jsonString, SingleTodoTask::class.java)
                }
                onSuccess(Constants.ADDED)
            }

        })

    }

    override fun getPersonalTodos(
        onSuccess: (ToDosResponse) -> Unit,
        onFailed: (IOException) -> Unit, presenter: HomePresenter
    ) {
        val request = getRequest(Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (e is UnAuthorizedException) {
                    presenter.onUnauthorizedError()
                }
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    val personalTodosResponse =
                        gson.fromJson(jsonString, ToDosResponse::class.java)
                    onSuccess(personalTodosResponse)
                }
            }

        })

    }

    override fun updatePersonalTodosStatus(
        personalTodoUpdateRequest: UpdateTodoTask,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit
    ) {
        val formBody = FormBody.Builder().add(Constants.Todo.ID, personalTodoUpdateRequest.id)
            .add(Constants.Todo.STATUS, personalTodoUpdateRequest.status.toString())
            .build()
        val request = putRequest(formBody, Constants.EndPoints.personalTodo)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    gson.fromJson(jsonString, ToDosResponse::class.java)
                }
                onSuccess(Constants.UPDATED)
            }

        })

    }
}