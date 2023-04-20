package com.tahaproject.todoy_app.ui.todo.personal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.tahaproject.todoy_app.databinding.ItemCardPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo


class PersonalAdapter(
    private var todos: List<Todo>,
    private val listener: PersonalAdapterListener
) :
    BaseRecyclerAdapter<Todo, ItemCardPersonalTodoBinding>(todos), Filterable {
    private var filteredTodos: List<Todo> = todos

    fun filter(query: String) {
        val filteredTodos = if (query.isEmpty()) {
            todos
        } else {
            todos.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
        this.list = filteredTodos
        notifyDataSetChanged()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardPersonalTodoBinding =
        ItemCardPersonalTodoBinding::inflate

    override fun bindViews(binding: ItemCardPersonalTodoBinding, currentItem: Todo) {
        binding.apply {
            textViewTitlePersonalTodo.text = currentItem.title
            textViewBodyPersonalTodo.text = currentItem.description
            textViewCreationTime.text = currentItem.status.toString()
            textViewStatus.text = currentItem.status.toString()
            root.setOnClickListener {
                listener.onClickItem(currentItem)
            }
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}