package com.tahaproject.todoy_app.ui.todo.team.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter


class TeamAdapter(private val list: List<Todo>) :
    BaseRecyclerAdapter<Todo, ItemCardTeamTodoBinding>(list) {

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardTeamTodoBinding =
        ItemCardTeamTodoBinding::inflate


    override fun bindViews(binding: ItemCardTeamTodoBinding, currentItem: Todo) {
        binding.apply {
            textViewTitleTeamTodo.text = currentItem.title
            textViewAssignName.text = currentItem.assignee
            textViewBodyTeamTodo.text = currentItem.description
            textViewStatus.text = currentItem.status.toString()
            textViewCreationTime.text = currentItem.creationTime
        }
    }

}