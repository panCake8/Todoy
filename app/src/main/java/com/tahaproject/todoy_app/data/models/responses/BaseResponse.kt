package com.tahaproject.todoy_app.data.models.responses

class BaseResponse<T>(
    override val value: T,
    override val message: String?,
    override val isSuccess: Boolean
) : IBaseResponse<T>
