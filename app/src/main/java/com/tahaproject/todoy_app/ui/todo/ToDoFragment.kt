package com.tahaproject.todoy_app.ui.todo


import androidx.viewbinding.ViewBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment


abstract class ToDoFragment<VB : ViewBinding,T> : BaseFragment<VB,T>() {
    abstract fun setup()
    abstract fun addCallBack()
}