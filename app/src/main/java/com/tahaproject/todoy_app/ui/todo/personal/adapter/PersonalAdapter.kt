package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
<<<<<<< HEAD
import com.tahaproject.todoy_app.data.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class PersonalAdapter(view: List<PersonalTodosResponse>) :
    BaseRecyclerAdapter<PersonalTodosResponse, ItemCardPersonalTodoBinding>(view) {
=======
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class PersonalAdapter(private val view: List<ToDosResponse>) :
    BaseRecyclerAdapter<ToDosResponse, ItemCardPersonalTodoBinding>(view) {
>>>>>>> fix/develop-fix-conflict
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