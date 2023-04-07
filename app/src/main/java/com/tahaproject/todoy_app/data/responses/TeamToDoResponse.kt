package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class TeamToDoResponse(
    @SerializedName("value")  val value : TeamToDo?,
    @SerializedName("message")  val message :String?,
    @SerializedName("isSuccess")  val isSuccess :Boolean?
)
