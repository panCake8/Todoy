package com.tahaproject.todoy_app.data.responses
import com.google.gson.annotations.SerializedName
data class TeamTodoUpdateResponse(
    @SerializedName("value") val  value:String?,
    @SerializedName("message")  val message :String?,
    @SerializedName("isSuccess")  val isSuccess :Boolean?
)
