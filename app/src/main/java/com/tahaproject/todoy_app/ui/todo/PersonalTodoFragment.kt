package com.tahaproject.todoy_app.ui.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding


class PersonalTodoFragment : ToDoFragment<FragmentPersonalTodoBinding>() {
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

    }


}