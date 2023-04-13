package com.tahaproject.todoy_app.data.responses


data class ContentLoginResponse(
    val value: Value,
    val message: String?,
    val isSuccess: Boolean,
)

data class Value(
    val token: String,
    val expireAt: String,
)

