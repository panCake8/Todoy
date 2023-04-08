package com.tahaproject.todoy_app.data.requests


import com.google.gson.annotations.SerializedName

data class TeamToDoUpdateRequest(
    val id: String?,
    val status: Int?,
)
