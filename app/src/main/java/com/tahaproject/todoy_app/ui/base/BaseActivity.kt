package com.tahaproject.todoy_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding,T> : AppCompatActivity() {
    lateinit var binding: VB
    abstract val bindingInflate: (LayoutInflater) -> VB
    abstract val presenter: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflate(layoutInflater)
        setContentView(binding.root)
    }

}