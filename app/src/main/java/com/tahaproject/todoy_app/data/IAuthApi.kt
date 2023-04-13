package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.*

interface IAuthApi {
    fun login(loginRequest: LoginRequest)
    fun register(registerRequest: RegisterRequest)
}


