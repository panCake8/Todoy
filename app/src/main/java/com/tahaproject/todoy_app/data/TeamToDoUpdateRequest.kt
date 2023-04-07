package com.tahaproject.todoy_app.data



import com.google.gson.annotations.SerializedName

data class TeamToDoUpdateRequest(
    @SerializedName("id")   val id: String?,
    @SerializedName("status")   val status:Int?,
)
