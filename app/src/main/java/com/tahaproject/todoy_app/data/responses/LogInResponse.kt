package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName
import com.tahaproject.todoy_app.data.responses.ContentLoginResponse

data class LogInResponse(
    @SerializedName("value") val value: ContentLoginResponse?,
    @SerializedName("message") val message: String?,
    @SerializedName("isSuccess") val isSuccess: Boolean?
)
