package com.tahaproject.todoy_app.ui.addtask.presenter

import okio.IOException

interface IAddNewTaskContract {
    interface View {
        fun showTaskAdded(successMessage: String)
        fun showError(error: IOException)
        fun showInvalidTitleMassage(titleMessage: String)
        fun showInvalidDescriptionMassage(descriptionMessage: String)

        fun showInvalidAssigneeMassage(assigneeMessage: String)
    }

    interface Presenter {
        fun addPersonalTask(title: String, description: String)
        fun addTeamTask(title: String, description: String, assignee: String?)

        fun isValid(title: String, description: String, assignee: String?): Boolean
    }
}
