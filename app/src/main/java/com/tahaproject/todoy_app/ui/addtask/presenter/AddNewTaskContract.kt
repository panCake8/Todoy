package com.tahaproject.todoy_app.ui.addtask.presenter

interface AddNewTaskContract {
    interface View {
        fun showTaskAddedSuccessfully()
        fun showTaskAdditionFailed()
    }

    interface Presenter {
        fun onAddTaskButtonClick(taskName: String)
    }
}
