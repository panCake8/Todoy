package com.tahaproject.todoy_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class TaskDetails(
    val title: String,
    val description: String?,
    val creationTime: String?,
    val taskStatus: String?,
    val assigns: List<String>,
):Parcelable
