package com.tahaproject.todoy_app.util

object Constants {
    const val TOKEN =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjdmOGZlOTY2LWQzYjItNDk1My1iNDBkLWY0YzAwNmM1YTUwMCIsInRlYW1JZCI6ImYwOTdmOTQzLWU0YTMtNDZmNC04NDAyLTdjOWI1NjUzMzc1NiIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxMDczNTMyfQ.RQ2ap5KFtsZC1ienRVze8BbhtvSNRH0PV6BOPeQYrrQ"
    const val URL = "https://team-todo-62dmq.ondigitalocean.app"
    const val AUTH = "Authorization"
    const val BEARER = "Bearer"
    const val EMPTY_STRING = "Bearer"

    object SharedPref {
        const val SHARED_PREF_NAME = "MySharedPref"
        private const val KEY_USERNAME = "UserName"
        private const val KEY_PASSWORD = "Password"
        private const val LOGOUT = "Logout"
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