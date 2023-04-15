package com.tahaproject.todoy_app.data.models.responses.signupResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpValue(
    val userId: String?,
    val username: Boolean,
): Parcelable


