package com.tahaproject.todoy_app.ui.addtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddNewTaskPresenter(requireContext())

        binding.chipTeamTodo.isClickable = false


        binding.buttonAdd.setOnClickListener {
            val title =  binding.editTextAddTaskTitle.text.toString()
            val description = binding.editTextAddTaskDescription.text.toString()
            val assignee =  binding.editTextAddAssigneeName.text.toString()
            chooseChips(title, description, assignee)

        }


    }
    private fun chooseChips(title: String, description: String, assignee: String) {
        binding.chipGroupPersonalTeam.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.size != 0) {
                val chip: Chip? = group.findViewById(checkedIds[0])
                chip?.let {
                    if (it.text.toString() == getString(R.string.personal_todo)) {
                        binding.chipPersonalTodo.isClickable = true
                        binding.chipTeamTodo.isClickable = false
                        presenter.addPersonalTask(title, description)
                    } else if (it.text.toString() == getString(R.string.team_todo)) {
                        binding.chipTeamTodo.isClickable = false
                        binding.chipPersonalTodo.isClickable = true
                        presenter.addTeamTask(title, description, assignee)
                    }
                }
            }
        }
    }



    override fun showTaskAdded(task: String) {
        showToast(task)
    }

    override fun showError(error: IOException) {
        Log.ERROR
    }

}