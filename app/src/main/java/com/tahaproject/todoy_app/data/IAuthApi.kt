package com.tahaproject.todoy_app.data

import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.requests.RegisterRequest
import com.tahaproject.todoy_app.data.responses.*

interface IAuthApi {
    fun login(loginRequest: LoginRequest,
              onSuccess: (LogInResponse) -> Unit,
              onFailure: (Throwable) -> Unit)
    fun register(registerRequest: RegisterRequest,
                 onSuccess: (RegisterResponse) -> Unit,
                 onFailure: (Throwable) -> Unit)
}



