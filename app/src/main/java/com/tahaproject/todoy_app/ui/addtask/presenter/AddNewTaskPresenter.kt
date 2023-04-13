package com.tahaproject.todoy_app.ui.addtask.presenter

class AddNewTaskPresenter(private val view: AddNewTaskContract.View) : AddNewTaskContract.Presenter {

    override fun onAddTaskButtonClick(taskName: String) {
        // Perform API call to add new task
        // Handle success or failure
        if (true) { // change with something usefull
            view.showTaskAddedSuccessfully()
        } else {
            view.showTaskAdditionFailed()
        }
    }
}