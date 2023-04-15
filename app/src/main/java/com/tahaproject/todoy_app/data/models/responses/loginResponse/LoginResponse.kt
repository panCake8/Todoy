package com.tahaproject.todoy_app.data.models.responses.loginResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tahaproject.todoy_app.data.models.responses.BaseResponse
import com.tahaproject.todoy_app.data.models.responses.IBaseResponse
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class LoginResponse(
    override val value: LoginValue,
    override val message: String?,
    override val isSuccess: Boolean,
) : IBaseResponse<LoginValue> by BaseResponse(value, message, isSuccess), Parcelable


//@Parcelize
//data class LoginResponse(
//    val loginValue: LoginValue,
//    val baseResponse: BaseResponse<LoginValue>
//) : Parcelable
