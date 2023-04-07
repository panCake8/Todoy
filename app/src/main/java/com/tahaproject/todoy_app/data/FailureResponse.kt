package com.tahaproject.todoy_app.data

import com.google.gson.annotations.SerializedName

data class FailureResponse(
    @SerializedName("value") val value: ContentLoginResponse?,
    @SerializedName("message") val message: String?,
    @SerializedName("isSuccess") val isSuccess: Boolean?
)

