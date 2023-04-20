package com.tahaproject.todoy_app.ui.todo.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.util.filterBySearch


class TeamAdapter(
    private var todos: List<Todo>,
    private val listener: TeamAdapterListener
) :
    BaseRecyclerAdapter<Todo, ItemCardTeamTodoBinding>(todos) {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardTeamTodoBinding =
        ItemCardTeamTodoBinding::inflate

    fun filterTeamTodosBySearch(query: String) {
        val filteredTodos = todos.filterBySearch(query, Todo::title, Todo::description, Todo::assignee)
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