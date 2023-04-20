package com.tahaproject.todoy_app.data.models.responses.todosListResponse

import android.os.Parcelable
import com.tahaproject.todoy_app.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val assignee: String = Constants.PERSONAL,
    val creationTime: String,
) : Parcelable



