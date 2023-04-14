package com.tahaproject.todoy_app.data.models.responses


import kotlinx.parcelize.Parcelize
import android.os.Parcelable


data class TeamToDosResponse(
    val value: List<TeamToDo>,
    val message: String?,
    val isSuccess: Boolean
) {
    data class TeamToDo(
        val id: String,
        val title: String,
        val description: String,
        val assignee: String,
        val status: Int,
        val creationTime: String
    )


}
