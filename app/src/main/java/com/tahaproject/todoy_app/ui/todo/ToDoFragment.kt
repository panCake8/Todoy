package com.tahaproject.todoy_app.ui.todo


import androidx.viewbinding.ViewBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamContract


abstract class ToDoFragment<VB : ViewBinding> : BaseFragment<ITeamContract.IView,VB>() {
    abstract fun setup()
    abstract fun addCallBack()
}