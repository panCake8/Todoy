package com.tahaproject.todoy_app.data.requests


import com.google.gson.annotations.SerializedName

data class TeamToDoPostRequest(
    @SerializedName("title") val title: String?,
    @SerializedName("assignee") val assignee: String?,
    @SerializedName("description") val description: String?,
)
