package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName
import com.tahaproject.todoy_app.data.responses.ContentLoginResponse

data class FailureResponse(
 val value: ContentLoginResponse?,
 val message: String?,
 val isSuccess: Boolean?
)

