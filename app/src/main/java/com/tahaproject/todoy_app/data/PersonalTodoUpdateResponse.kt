package com.tahaproject.todoy_app.data

import com.google.gson.annotations.SerializedName

data class PersonalTodoUpdateResponse(
    @SerializedName("value") val  value:String?,
    @SerializedName("message")  val message :String?,
    @SerializedName("isSuccess")  val isSuccess :Boolean?
)
