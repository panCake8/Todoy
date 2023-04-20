package com.tahaproject.todoy_app.util

object Constants {
    const val URL = "https://team-todo-62dmq.ondigitalocean.app"
    const val AUTH = "Authorization"
    const val BEARER = "Bearer"
    const val ONE_HUNDRED_PERCENT: Int = 100
    const val TODO_STATUS: Int = 0
    const val IN_PROGRESS_STATUS: Int = 1
    const val DONE_STATUS: Int = 2
    const val TODO_STRING: String = "Todo"
    const val IN_PROGRESS_STRING: String = "In progress"
    const val DONE_STRING: String = "Done"
    const val TASK_DETAILS: String = "taskDetails"
    const val EMPTY_STRING: String = "todoId"

    const val SHORT_NAME = 4
    const val SHORT_PASSWORD = 8

    const val ADDED = "Added!"
    const val UPDATED = "Updated"
    const val Home = "Home"
    const val DETAILS = "Details"

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