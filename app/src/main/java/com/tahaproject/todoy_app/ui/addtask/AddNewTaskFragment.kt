package com.tahaproject.todoy_app.ui.addtask


import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter
import com.tahaproject.todoy_app.ui.baseview.BaseBottomSheetDialogFragment


class AddNewTaskFragment : BaseBottomSheetDialogFragment<FragmentAddNewTaskBinding>(), AddNewTaskContract.View {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNewTaskBinding
        get() = FragmentAddNewTaskBinding::inflate

    private var selectedTaskChip: TaskChip = TaskChip.PERSONAL
    private lateinit var presenter: AddNewTaskContract.Presenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = AddNewTaskPresenter(requireContext())
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.choiceGroupChips.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_personal_todo -> TaskChip.PERSONAL
                R.id.chip_Team_todo -> TaskChip.TEAM
                else -> TaskChip.PERSONAL // Default value
            }
        }

        binding.chipTeamTodo.setOnClickListener {
            selectedTaskChip = TaskChip.TEAM
            binding.chipPersonalTodo.isChecked = false
            changeVisibility()
        }

        binding.chipPersonalTodo.setOnClickListener {
            selectedTaskChip = TaskChip.PERSONAL
            binding.chipTeamTodo.isChecked = false
            changeVisibility()
        }

        binding.buttonAdd.setOnClickListener {
            val title = binding.editTextAddTaskTitle.text.toString()
            val description = binding.editTextAddTaskDescription.text.toString()
            val assignee = binding.editTextAddAssigneeName.text.toString()

            when (selectedTaskChip) {
                TaskChip.TEAM -> presenter.addTeamTask(title, description, assignee)
                TaskChip.PERSONAL -> presenter.addPersonalTask(title, description)
            }
            hideBottomSheet()
        }
    }

    private fun changeVisibility() {
        with(binding.root as ViewGroup) {
            val textViewVisibility = binding.textviewAddAssigneeName.isVisible
            val editTextVisibility = binding.editTextAddAssigneeName.isVisible

            if (textViewVisibility && editTextVisibility) {
                // If both views are visible, set their visibility to gone
                transitionVisibility(binding.textviewAddAssigneeName, View.GONE)
                transitionVisibility(binding.editTextAddAssigneeName, View.GONE)
            } else {
                // If any of the views are gone, set their visibility to visible
                transitionVisibility(binding.textviewAddAssigneeName, View.VISIBLE)
                transitionVisibility(binding.editTextAddAssigneeName, View.VISIBLE)
            }
        }
    }

    private fun ViewGroup.transitionVisibility(view: View, visibility: Int) {
        if (view.visibility != visibility) {
            TransitionManager.beginDelayedTransition(this)
            view.visibility = visibility
        }
    }

    override fun showTaskAdded(task: String) {
        Toast.makeText(requireContext(), task, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Throwable) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { Log.i("TAG", it) }
        }
    }

    private fun hideBottomSheet() {
        dismiss()
        presenter.detachView()
    }
}

enum class TaskChip {
    PERSONAL, TEAM
}

