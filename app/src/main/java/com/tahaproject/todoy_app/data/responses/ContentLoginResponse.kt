package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class ContentLoginResponse(
    val token: String?,
   val expireAt: String?,
)

