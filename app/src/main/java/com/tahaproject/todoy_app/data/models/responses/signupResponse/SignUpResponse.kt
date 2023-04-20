package com.tahaproject.todoy_app.data.models.responses.signupResponse

import android.os.Parcelable
import com.tahaproject.todoy_app.data.models.responses.BaseResponse
import com.tahaproject.todoy_app.data.models.responses.IBaseResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpResponse(
    override val value: SignUpValue,
    override val message: String?,
    override val isSuccess: Boolean,
) : IBaseResponse<SignUpValue> by BaseResponse(value, message, isSuccess), Parcelable



