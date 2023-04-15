package com.tahaproject.todoy_app.data.models.responses.loginResponse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginValue(
    val token: String?,
    val expireAt: String?,
): Parcelable
