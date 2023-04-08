package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class PersonalTodoCreateResponse(
    @SerializedName("value")  val value : PersonalTodo?,
    @SerializedName("message")  val message :String?,
    @SerializedName("isSuccess")  val isSuccess :Boolean?
)