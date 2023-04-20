package com.tahaproject.todoy_app.ui.todo.personal.adapter

import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo

interface PersonalAdapterListener {
    fun onClickItem(item: Todo)
}