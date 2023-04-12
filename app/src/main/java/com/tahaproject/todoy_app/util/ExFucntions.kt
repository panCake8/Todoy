package com.tahaproject.todoy_app.util

import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment



fun SharedPreferences.put(func: SharedPreferences.Editor.() -> Unit) {
    edit().apply {
        func()
        apply()
    }
}

fun AppCompatActivity.showToast(message: Any) {
    Toast.makeText(this@showToast, message.toString(), Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: Any) {
    Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
}