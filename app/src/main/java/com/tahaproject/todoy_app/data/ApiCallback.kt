interface ApiCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(error: Throwable)
}

fun <T> executeRequest(apiCall: (ApiCallback<T>) -> Unit, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit) {
    val apiCallback = object : ApiCallback<T> {
        override fun onSuccess(result: T) {
            onSuccess(result)
        }

        override fun onFailure(error: Throwable) {
            onFailure(error)
        }
    }
    apiCall(apiCallback)
}
