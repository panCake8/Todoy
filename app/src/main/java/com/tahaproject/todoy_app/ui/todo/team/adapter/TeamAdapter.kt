package com.tahaproject.todoy_app.ui.todo.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo


class TeamAdapter(
    private var todos: List<Todo>,
    private val listener: TeamAdapterListener
) :
    BaseRecyclerAdapter<Todo, ItemCardTeamTodoBinding>(todos) {
    private var filteredTodos: List<Todo> = todos
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardTeamTodoBinding =
        ItemCardTeamTodoBinding::inflate

    fun filter(query: String) {
        val filteredTodos = if (query.isEmpty()) {
            todos
        } else {
            todos.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(
                    query,
                    ignoreCase = true
                ) || it.assignee.contains(query, ignoreCase = true)
            }
        }
        this.list = filteredTodos
        notifyDataSetChanged()
    }

    override fun bindViews(binding: ItemCardTeamTodoBinding, currentItem: Todo) {
        binding.apply {
            textViewTitleTeamTodo.text = currentItem.title
            textViewAssignName.text = currentItem.assignee
            textViewBodyTeamTodo.text = currentItem.description
            textViewStatus.text = currentItem.status.toString()
            textViewCreationTime.text = currentItem.creationTime
            root.setOnClickListener {
                listener.onClickItem(currentItem)
            }
        }
    }

}