package com.tahaproject.todoy_app.ui.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter
import com.tahaproject.todoy_app.ui.baseview.BaseBottomSheetDialogFragment
import java.io.IOException


class AddNewTaskFragment : BaseBottomSheetDialogFragment<FragmentAddNewTaskBinding>(), AddNewTaskContract.View {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNewTaskBinding
        get() = FragmentAddNewTaskBinding::inflate

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_task

    private lateinit var presenter: AddNewTaskContract.Presenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddNewTaskPresenter()

        binding.buttonAdd.setOnClickListener {
            val title =  binding.editTextAddTaskTitle.text.toString()
            val description = binding.editTextAddTaskDescription.text.toString()
            val assignee = if (binding.chipGroupPersonalTeam.checkedChipId == R.id.chip_personal_todo) binding.editTextAddAssigneeName.text.toString() else ""
            val status = 0

        }

        binding.chipTeamTodo.isClickable = false
        chooseChips()

    }
    private fun chooseChips() {
        binding.chipGroupPersonalTeam.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.size != 0) {
                val chip: Chip? = group.findViewById(checkedIds[0])
                chip?.let {
                    if (it.text.toString() == getString(R.string.personal_todo)) {
                        binding.chipPersonalTodo.isClickable = true
                        binding.chipTeamTodo.isClickable = false
                    } else if (it.text.toString() == getString(R.string.team_todo)) {
                        binding.chipTeamTodo.isClickable = false
                        binding.chipPersonalTodo.isClickable = true
                    }
                }
            }
        }
    }



    override fun showTaskAdded(task: String) {
        TODO("Not yet implemented")
    }

    override fun showError(error: IOException) {
        TODO("Not yet implemented")
    }

}