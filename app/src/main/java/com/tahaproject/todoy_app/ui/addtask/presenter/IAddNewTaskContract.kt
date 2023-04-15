package com.tahaproject.todoy_app.ui.addtask.presenter

import okio.IOException

interface IAddNewTaskContract {
    interface View {
        fun showTaskAdded(successMessage: String)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String)
    }
}
