package com.tahaproject.todoy_app.data.domain.responses


data class LoginResponse(
    val value: Value,
    val message: String?,
    val isSuccess: Boolean,
) {
    data class Value(
        val token: String,
        val expireAt: String,
    )
}



