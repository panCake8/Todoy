package com.tahaproject.todoy_app.data.models.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ToDosResponse<T>(
    val value: List<@RawValue T>,
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

    @Parcelize
    data class TeamTodo(
        val id: String,
        val title: String,
        val description: String,
        val assignee: String,
        val status: Int,
        val creationTime: String
    ) : Parcelable
}


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