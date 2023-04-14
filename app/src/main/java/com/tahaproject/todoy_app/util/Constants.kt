package com.tahaproject.todoy_app.util

object Constants {
    const val TOKEN =
        "eyJ0eXAiOiJV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjI3NGE4MTQwLTZkMjYtNGY3Ny1hMzZmLWJmMjc1Y2Y1MDhhZCIsInRlYW1JZCI6ImYwOTdmOTQzLWU0YTMtNDZmNC04NDAyLTdjOWI1NjUzMzc1NiIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNTc4ODUwfQ.tzkNUgKO1Z7Z6p9RDnCBETxDgPk_2Gxz-2ODCY-iuMY"
    const val URL = "https://team-todo-62dmq.ondigitalocean.app"
    const val AUTH = "Authorization"
    const val BEARER = "Bearer"
    const val EMPTY_STRING = "Bearer"

    const val ADDED = "Added!"
    const val UPDATED = "Updated"
    const val DETAILS="Details"

    object SharedPref {
        const val SHARED_PREF_NAME = "MySharedPref"
        private const val KEY_USERNAME = "UserName"
        private const val KEY_PASSWORD = "Password"
    }

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