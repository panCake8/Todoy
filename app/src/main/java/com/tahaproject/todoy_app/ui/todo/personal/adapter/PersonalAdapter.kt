package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class PersonalAdapter(view: List<PersonalTodosResponse>) :
    BaseRecyclerAdapter<PersonalTodosResponse, ItemCardPersonalTodoBinding>(view) {
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