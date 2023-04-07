package com.tahaproject.todoy_app.data

import com.google.gson.annotations.SerializedName

data class TeamToDo(
    @SerializedName("id") val id: String?,
    @SerializedName("assignee") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("creationTime") val creationTime: String?
)
