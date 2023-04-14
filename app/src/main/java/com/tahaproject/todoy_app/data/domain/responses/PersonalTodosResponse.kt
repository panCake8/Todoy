package com.tahaproject.todoy_app.data.domain.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalTodosResponse(
    val value: List<PersonalTodo>,
    val message: String?,
    val isSuccess: Boolean,
) : Parcelable {
    @Parcelize
    data class PersonalTodo(
        val id: String,
        val title: String,
        val description: String,
        val status: Int,
        val creationTime: String,
    ) : Parcelable
}