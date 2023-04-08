package com.tahaproject.todoy_app.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    override fun setup() {

    }

    override fun addCallBack() {
        binding.appBarTeamTodo.setNavigationOnClickListener {
            back()
        }
    }


}