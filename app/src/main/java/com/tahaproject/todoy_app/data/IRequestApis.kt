package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.*

interface IRequestApis {
    fun login(): LogInResponse

    fun register(): RegisterResponse

    fun createPersonalTodo(): PersonalTodoCreateResponse

    fun getPersonalTodos():PersonalTodo

    fun updatePersonalTodosStatus():PersonalTodoUpdateResponse

    fun createTeamTodo()

    fun getTeamTodos()

    fun updateTeamTodosStatus()

}