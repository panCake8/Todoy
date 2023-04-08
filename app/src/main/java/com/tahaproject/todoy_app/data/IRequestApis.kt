package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.*

interface IAuthApi  {
    fun login(): LogInResponse
    fun register(): RegisterResponse
}

interface IPersonalTodoApi  {
    fun createPersonalTodo(): PersonalTodoCreateResponse
    fun getPersonalTodos():PersonalTodo
    fun updatePersonalTodosStatus():PersonalTodoUpdateResponse
}

interface ITeamTodoApi  {
    fun createTeamTodo() :TeamToDoResponse
    fun getTeamTodos() :TeamToDo
    fun updateTeamTodosStatus() :TeamTodoUpdateResponse
}
