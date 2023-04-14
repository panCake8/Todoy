package com.tahaproject.todoy_app.data.models.requests


data class PersonalTodoRequest(
    val value: PersonalTodo,
    val message: String?,
    val isSuccess: Boolean
) {
    data class PersonalTodo(
        val id: String,
        val title: String,
        val description: String,
        val asignee: String,
        val status: Int,
        val creationTime: String
    )

}