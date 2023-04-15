package com.tahaproject.todoy_app.ui.addtask.presenter


interface AddNewTaskContract {
    interface View {
        fun showTaskAdded(task: String)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String)
    }
}