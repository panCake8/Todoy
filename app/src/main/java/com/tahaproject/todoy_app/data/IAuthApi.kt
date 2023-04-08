package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.responses.*

interface IAuthApi {
    fun login(): LogInResponse
    fun register(): RegisterResponse
}


