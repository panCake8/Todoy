package com.tahaproject.todoy_app.ui.baseview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB
    abstract val bindingInflate: (LayoutInflater) -> VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflate(layoutInflater)
        setContentView(binding.root)
    }

}