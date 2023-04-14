package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.baseview.BaseRecyclerAdapter

class PersonalAdapter(private val view: List<PersonalTodosResponse.PersonalTodo>) :
    BaseRecyclerAdapter<PersonalTodosResponse.PersonalTodo, ItemCardPersonalTodoBinding>(view) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardPersonalTodoBinding =
        ItemCardPersonalTodoBinding::inflate

    override fun bindViews(binding: ItemCardPersonalTodoBinding) {
        binding.apply {
            //textViewTitlePersonalTodo.text = list[0].title
            //textViewBodyPersonalTodo.text = list[0].description
            //textViewCreationTime.text = list[0].status.toString()
            //textViewStatus.text = list[0].status.toString()
        }

    }
}