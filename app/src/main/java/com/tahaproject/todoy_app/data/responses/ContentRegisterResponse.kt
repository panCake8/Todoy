package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class ContentRegisterResponse(
    @SerializedName("userId") val userId: String?,
    @SerializedName("username") val username: String?,
)

