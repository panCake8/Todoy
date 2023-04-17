package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.personal.presenter.PersonalTodoPresenter


class PersonalTodoFragment : ToDoFragment<FragmentPersonalTodoBinding,PersonalTodoPresenter>() {
     override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override fun setup() {

    }

    override fun addCallBack() {
        }

    override val presenter: PersonalTodoPresenter
        get() = TODO("Not yet implemented")


}