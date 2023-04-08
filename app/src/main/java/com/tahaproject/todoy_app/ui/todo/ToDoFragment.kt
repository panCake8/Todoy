package com.tahaproject.todoy_app.ui.todo


import androidx.viewbinding.ViewBinding

import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition


abstract class ToDoFragment<VB : ViewBinding> : BaseFragmentWithTransition<VB>() {
    abstract fun setup()
    abstract fun addCallBack()
}