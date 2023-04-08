package com.tahaproject.todoy_app.data.requests


import com.google.gson.annotations.SerializedName

data class TeamToDoPostRequest(
    val title: String?,
     val assignee: String?,
   val description: String?,
)
