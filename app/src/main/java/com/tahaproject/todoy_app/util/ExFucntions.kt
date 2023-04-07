package com.tahaproject.todoy_app.util

import android.content.SharedPreferences


fun SharedPreferences.put(func: SharedPreferences.Editor.() -> Unit) {
    edit().apply {
        func()
        apply()
    }
}