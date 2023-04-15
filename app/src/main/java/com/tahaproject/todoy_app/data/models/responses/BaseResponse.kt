package com.tahaproject.todoy_app.data.models.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

//@Parcelize
//class BaseResponse<T>(
//    val value: @RawValue T,
//    @SerializedName("message")  val message: String?,
//    @SerializedName("isSuccess")  val isSuccess: Boolean,
//) : Parcelable

interface IBaseResponse<T> {
    val value: T
    val message: String?
    val isSuccess: Boolean
}


class BaseResponse<T>(
    override val value: T,
    override val message: String?,
    override val isSuccess: Boolean
) : IBaseResponse<T>
