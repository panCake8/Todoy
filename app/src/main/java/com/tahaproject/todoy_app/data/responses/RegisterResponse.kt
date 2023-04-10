package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
     val value: ContentRegisterResponse?,
     val message: String?,
     val isSuccess: Boolean?
)
