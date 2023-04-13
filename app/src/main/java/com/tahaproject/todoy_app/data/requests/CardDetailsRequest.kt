package com.tahaproject.todoy_app.data.requests

data class CardDetailsRequest(
    val value: List<Card>,
    val message: String,
    val isSuccess: Boolean

)
