package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("value") val value: ContentRegisterResponse?,
    @SerializedName("message") val message: String?,
    @SerializedName("isSuccess") val isSuccess: Boolean?
)
