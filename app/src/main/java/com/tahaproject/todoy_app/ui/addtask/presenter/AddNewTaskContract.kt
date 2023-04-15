package com.tahaproject.todoy_app.ui.addtask.presenter

interface AddNewTaskContract {
    interface View {
        fun showTaskAdded(successMessage: String)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String)
    }
}