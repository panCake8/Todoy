package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.LogInResponse
import com.tahaproject.todoy_app.data.responses.RegisterResponse

interface IRequestApis {
    fun login(): LogInResponse

    fun register(): RegisterResponse

    fun createPersonalTodo()

    fun getPersonalTodos()

    fun updatePersonalTodosStatus()

    fun createTeamTodo()

    fun getTeamTodos()

    fun updateTeamTodosStatus()

}