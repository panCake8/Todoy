package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class PersonalTodoCreateResponse(
    val value : PersonalTodo?,
    val message :String?,
     val isSuccess :Boolean?
)