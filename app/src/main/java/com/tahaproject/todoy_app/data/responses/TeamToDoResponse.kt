package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class TeamToDoResponse(
    val value : TeamToDo?,
    val message :String?,
    val isSuccess :Boolean?
)
