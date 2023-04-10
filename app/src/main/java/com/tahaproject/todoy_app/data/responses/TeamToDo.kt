package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class TeamToDo(
     val id: String?,
     val title:String?,
     val assignee: String?,
     val description: String?,
     val status: Int?,
     val creationTime: String?
)


