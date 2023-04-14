package com.tahaproject.todoy_app.ui.addtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding

import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter
import com.tahaproject.todoy_app.ui.baseview.BaseBottomSheetDialogFragment
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class AddNewTaskFragment : BaseBottomSheetDialogFragment<FragmentAddNewTaskBinding>(), AddNewTaskContract.View {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNewTaskBinding
        get() = FragmentAddNewTaskBinding::inflate

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_task

    private lateinit var presenter: AddNewTaskContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = AddNewTaskPresenter(requireContext())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipPersonalTodo.isClickable = false
        binding.buttonAdd.setOnClickListener {
            val title =  binding.editTextAddTaskTitle.text.toString()
            val description = binding.editTextAddTaskDescription.text.toString()
            val assignee =  binding.editTextAddAssigneeName.text.toString()
            // todo handel the chips logic
            presenter.addTeamTask(title, description, assignee)
            presenter.addPersonalTask(title, description)

        }
    }

    override fun showTaskAdded(task: String) {
        showToast(task)
    }

    override fun showError(error: IOException) {
        Log.e("TAG", "Error: ${error.message}") // Replace TAG with your desired log tag
    }

}