package com.tahaproject.todoy_app.data.responses

import com.google.gson.annotations.SerializedName

data class PersonalTodoCreateResponse(
    @SerializedName("value")  val value : PersonalTodo?,
    @SerializedName("message")  val message :String?,
    @SerializedName("isSuccess")  val isSuccess :Boolean?
)


data class LoginRequest(
    val username: String,
    val password: String
)

/*
val client = OkHttpClient()

// create a LoginRequest object with the username and password
val loginRequest = LoginRequest("myusername", "mypassword")

// convert the LoginRequest object to a JSON string
val requestBody = Gson().toJson(loginRequest)

// create a new request with the JSON string as the request body
val request = Request.Builder()
    .url("https://example.com/login")
    .post(requestBody.toRequestBody(MediaType.parse("application/json")))
    .build()

// execute the request and handle the response
client.newCall(request).enqueue(object : Callback {
    override fun onFailure(call: Call, e:   IOException) {
        // handle the error
    }

    override fun onResponse(call: Call, response: Response) {
        // handle the response
    }
})
*/