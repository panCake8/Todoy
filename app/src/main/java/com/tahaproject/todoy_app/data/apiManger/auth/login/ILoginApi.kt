package com.tahaproject.todoy_app.data.apiManger.auth.login

import com.tahaproject.todoy_app.data.domain.requests.LoginRequest
import com.tahaproject.todoy_app.data.domain.responses.LoginResponse
import java.io.IOException

interface ILoginApi {
    fun login(
        loginRequest: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onFailed: (IOException) -> Unit,
    )
}