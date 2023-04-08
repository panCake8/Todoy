package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.LogInResponse

interface IRequestApis {
    fun login(): LogInResponse

    fun register()

    fun createPersonalTodo()

    fun getPersonalTodos()

    fun updatePersonalTodosStatus()

    fun createTeamTodo()

    fun getTeamTodos()

    fun updateTeamTodosStatus()

}