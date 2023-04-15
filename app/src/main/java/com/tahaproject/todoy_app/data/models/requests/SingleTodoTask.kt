package com.tahaproject.todoy_app.data.models.requests

data class SingleTodoTask(
    val title: String,
    val description: String,
    val assignee: String = "",
    val status: Int
)
