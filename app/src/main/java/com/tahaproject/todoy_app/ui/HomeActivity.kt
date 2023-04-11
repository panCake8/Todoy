package com.tahaproject.todoy_app.ui


import android.os.Bundle
import android.view.LayoutInflater
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseActivity
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url=URL("https://jsonplaceholder.typicode.com/todos/1")
        val connection=url.openConnection()
        val inputStream=connection.getInputStream()
        val result=InputStreamReader(inputStream).readText()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}