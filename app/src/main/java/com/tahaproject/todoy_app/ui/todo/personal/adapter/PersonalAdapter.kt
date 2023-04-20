package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.util.filterBySearch


class PersonalAdapter(
    private var todos: List<Todo>,
    private val listener: PersonalAdapterListener
) :
    BaseRecyclerAdapter<Todo, ItemCardPersonalTodoBinding>(todos) {


    fun filterPersonalTodosBySearch(query: String) {
        val filteredTodos = todos.filterBySearch(query, Todo::title, Todo::description)
        this.list = filteredTodos
        notifyDataSetChanged()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardPersonalTodoBinding =
        ItemCardPersonalTodoBinding::inflate

    override fun bindViews(binding: ItemCardPersonalTodoBinding, currentItem: Todo) {
        binding.apply {
            textViewTitlePersonalTodo.text = currentItem.title
            textViewBodyPersonalTodo.text = currentItem.description
            textViewCreationTime.text = currentItem.creationTime.substring(0,10)
            textViewStatus.text = currentItem.status.toString()
            root.setOnClickListener {
                listener.onClickItem(currentItem)
            }
        }
    }


}