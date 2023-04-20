package com.tahaproject.todoy_app.ui.todo.team.adapter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo

interface TeamAdapterListener {
    fun onClickItem(item: Todo)
}