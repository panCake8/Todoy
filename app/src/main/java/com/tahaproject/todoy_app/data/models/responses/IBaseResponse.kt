package com.tahaproject.todoy_app.data.models.responses

interface IBaseResponse<T> {
    val value: T
    val message: String?
    val isSuccess: Boolean
}