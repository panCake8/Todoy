package com.tahaproject.todoy_app.data.domain.requests

data class TeamToDoPostRequest(
    val value: TeamTodo,
    val message: String?,
    val isSuccess: Boolean,

    ) {
    data class TeamTodo(
        val id: String,
        val title: String,
        val description: String,
        val assignee: String,
        val status: Int,
        val creationTime: String,
    )
}
