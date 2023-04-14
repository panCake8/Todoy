package com.tahaproject.todoy_app.data.models.responses

data class SignUpResponse(
    val value: Value,
    val message: String?,
    val isSuccess: Boolean

) {
    data class Value(
        val userId: String,
        val username: String,
    )
}
