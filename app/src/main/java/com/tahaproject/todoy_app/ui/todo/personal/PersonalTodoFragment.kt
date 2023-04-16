package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.todo.ToDoFragment


class PersonalTodoFragment : ToDoFragment<FragmentPersonalTodoBinding>() {
     val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {

    }


}