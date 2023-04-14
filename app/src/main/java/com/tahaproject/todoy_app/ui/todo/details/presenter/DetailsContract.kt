package com.tahaproject.todoy_app.ui.todo.details.presenter

import com.tahaproject.todoy_app.data.models.TaskDetails

sealed interface DetailsContract {
    interface MyView {
        fun showData(taskDetails: TaskDetails)
    }
    interface Presenter {
        fun getData()
    }
}