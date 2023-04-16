package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamContract


class PersonalTodoFragment(override val presenter: ITeamContract.IView) : ToDoFragment<FragmentPersonalTodoBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    override fun setup() {

    }

    override fun addCallBack() {
        binding.appBarPersonalTodo.setNavigationOnClickListener {
//            back()
        }
    }


}