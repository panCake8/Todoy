package com.tahaproject.todoy_app.ui.addtask.presenter

interface IAddNewTaskContract {
    interface IView {
        fun showTaskAdded(successMessage: String)
        fun showError(error: Throwable)
    }

    interface IPresenter {
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String)
    }
}
