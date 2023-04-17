package com.tahaproject.todoy_app.data.apiManger.auth.signup

import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import java.io.IOException

interface ISignUpApi {
    fun signUp(
        signUpRequest: SignUpRequest,
        onSuccess: (String) -> Unit,
        onFailed: (IOException) -> Unit,
    )
}


