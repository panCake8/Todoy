package com.tahaproject.todoy_app.ui.todo


import androidx.viewbinding.ViewBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment


abstract class ToDoFragment<VB : ViewBinding> : BaseFragment<VB>() {
    abstract fun setup()
    abstract fun addCallBack()
}