package com.tahaproject.todoy_app.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.showToast(message: Any) {
    Toast.makeText(this@showToast, message.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: Any) {
    Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
}

fun Int.todoPercentage(totalCount: Int) =
    (this.toFloat() / totalCount.toFloat()) * Constants.ONE_HUNDRED_PERCENT

fun <T> List<T>.filterBySearch(
    query: String,
    vararg propertiesToSearch: (T) -> String
): List<T> {
    if (query.isEmpty()) {
        return this
    }

    return filter { item ->
        propertiesToSearch.any { property ->
            property(item).contains(query, ignoreCase = true)
        }
    }
}