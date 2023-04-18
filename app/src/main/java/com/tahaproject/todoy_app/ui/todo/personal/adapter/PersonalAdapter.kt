package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo


class PersonalAdapter(private val view: List<Todo>) :
    BaseRecyclerAdapter<Todo, ItemCardPersonalTodoBinding>(view) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardPersonalTodoBinding =
        ItemCardPersonalTodoBinding::inflate

    override fun bindViews(binding: ItemCardPersonalTodoBinding, currentItem: Todo){
        binding.apply {
           textViewTitlePersonalTodo.text = currentItem.title
            textViewBodyPersonalTodo.text = currentItem.description
            textViewCreationTime.text = currentItem.status.toString()
            textViewStatus.text = currentItem.status.toString()
        }

    }
}