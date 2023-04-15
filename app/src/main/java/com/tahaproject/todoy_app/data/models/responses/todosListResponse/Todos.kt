package com.tahaproject.todoy_app.data.models.responses.todosListResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todos(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val assignee: String = "",
    val creationTime: String,
) : Parcelable



