package com.tahaproject.todoy_app.ui.todo.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
<<<<<<< HEAD
import com.tahaproject.todoy_app.data.responses.TeamToDosResponse
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class TeamAdapter(view: List<TeamToDosResponse.TeamToDo>) :
    BaseRecyclerAdapter<TeamToDosResponse.TeamToDo, ItemCardTeamTodoBinding>(view) {
=======
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.ItemCardTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseRecyclerAdapter

class TeamAdapter(private val view: List<ToDosResponse>) :
    BaseRecyclerAdapter<ToDosResponse, ItemCardTeamTodoBinding>(view) {
>>>>>>> fix/develop-fix-conflict
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemCardTeamTodoBinding =
        ItemCardTeamTodoBinding::inflate

    override fun bindViews(binding: ItemCardTeamTodoBinding) {
        binding.apply {
            //textViewTitleTeamTodo.text = list[0].title
            //textViewAssignName.text = list[0].assignee
            //textViewBodyTeamTodo.text = list[0].description
            //textViewStatus.text = list[0].status.toString()
            //textViewCreationTime.text = list[0].creationTime
        }
    }
}