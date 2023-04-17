package com.tahaproject.todoy_app.data.apiManger.auth.signup

import com.tahaproject.todoy_app.data.requests.SignUpRequest
import com.tahaproject.todoy_app.data.responses.SignUpResponse
import com.tahaproject.todoy_app.ui.signup.presenter.ISignUpContract
import java.io.IOException

interface ISignUpApi {
    fun signUp(
        signUpRequest: SignUpRequest,
        onSuccess: (SignUpResponse) -> Unit,
        onFailed: (IOException) -> Unit,
        presenter: ISignUpContract.IPresenter
    )
}


