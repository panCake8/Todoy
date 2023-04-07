package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class ContentLoginResponse(
    @SerializedName("token") val token: String?,
    @SerializedName("expireAt") val expireAt: String?,
)

