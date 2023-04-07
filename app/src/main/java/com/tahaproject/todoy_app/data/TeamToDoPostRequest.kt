package com.tahaproject.todoy_app.data


import com.google.gson.annotations.SerializedName

data class TeamToDoPostRequest(
    @SerializedName("title")   val title:String?,
    @SerializedName("assignee")   val title:String?,
    @SerializedName("description")   val description:String?,
)
