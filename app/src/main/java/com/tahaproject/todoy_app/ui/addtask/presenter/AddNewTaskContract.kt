package com.tahaproject.todoy_app.ui.addtask.presenter

import java.io.IOException

interface AddNewTaskContract {
    interface View {
        fun showTaskAdded(task: String)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String)
    }
}