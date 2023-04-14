package com.tahaproject.todoy_app.util

object Constants {
    const val URL = "https://team-todo-62dmq.ondigitalocean.app"
    const val AUTH = "Authorization"
    const val BEARER = "Bearer"
    const val EMPTY_STRING = "Bearer"

    const val ADDED = "Added!"
    const val UPDATED = "Updated"
    const val Home = "Home"

    object EndPoints {
        const val personalTodo = "todo/personal"
        const val signup = "signup"
        const val login = "login"
        const val teamTodo = "todo/team"
    }

    object Todo {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val ASSIGNEE = "assignee"
        const val ID = "id"
        const val STATUS = "status"
    }

}