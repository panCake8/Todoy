package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class TeamToDo(
    @SerializedName("id") val id: String?,
    @SerializedName("title")   val title:String?,
    @SerializedName("assignee") val assignee: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("status") val status: Int?,
    @SerializedName("creationTime") val creationTime: String?
)


