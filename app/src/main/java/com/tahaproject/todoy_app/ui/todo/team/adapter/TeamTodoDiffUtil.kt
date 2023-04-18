package com.tahaproject.todoy_app.ui.todo.team.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo

class TeamTodoDiffUtil(private val oldList: List<Todo>,private val newList: List<Todo>):
       DiffUtil.Callback(){

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

}