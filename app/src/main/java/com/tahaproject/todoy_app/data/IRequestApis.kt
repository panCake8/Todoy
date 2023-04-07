package com.tahaproject.todoy_app.data

interface IRequestApis {
    fun login()

    fun register()

    fun createPersonalTodo()

    fun getPersonalTodos()

    fun updatePersonalTodosStatus()

    fun createTeamTodo()

    fun getTeamTodos()

    fun updateTeamTodosStatus()

}