package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class PersonalTodoUpdateResponse(
    val  value:String?,
    val message :String?,
    val isSuccess :Boolean?
)
