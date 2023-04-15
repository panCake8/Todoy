package com.tahaproject.todoy_app.ui.addtask


import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiImpl
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApiImpl
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.addtask.presenter.IAddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter
import com.tahaproject.todoy_app.ui.baseview.BaseBottomSheetDialogFragment
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class AddNewTaskFragment : BaseBottomSheetDialogFragment<FragmentAddNewTaskBinding>(), IAddNewTaskContract.View {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNewTaskBinding
        get() = FragmentAddNewTaskBinding::inflate

    private var selectedTaskChip: TaskChip = TaskChip.PERSONAL
    private lateinit var addNewTaskPresenter: IAddNewTaskContract.Presenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNewTaskPresenter = AddNewTaskPresenter(this, PersonalTodoApiImpl(requireContext()), TeamTodoApiImpl(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        chooseGroup()
        addCallback()

    }

    private fun chooseGroup() {
        binding.choiceGroupChips.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_personal_todo -> TaskChip.PERSONAL
                R.id.chip_Team_todo -> TaskChip.TEAM
                else -> TaskChip.PERSONAL // Default value
            }
        }
    }

    private fun addCallback() {
        binding.chipTeamTodo.setOnClickListener { onChipTeamClicked() }

        binding.chipPersonalTodo.setOnClickListener { onChipPersonalClicked() }

        binding.buttonAdd.setOnClickListener { onButtonAddClicked() }
    }

    private fun onChipTeamClicked() {
        selectedTaskChip = TaskChip.TEAM
        binding.chipPersonalTodo.isChecked = false
        showAssignee()
    }

    private fun onChipPersonalClicked() {
        selectedTaskChip = TaskChip.PERSONAL
        binding.chipTeamTodo.isChecked = false
        hideAssignee()
    }

    private fun onButtonAddClicked() {
        val title = binding.editTextAddTaskTitle.text.toString()
        val description = binding.editTextAddTaskDescription.text.toString()
        val assignee = binding.editTextAddAssigneeName.text.toString()

        when (selectedTaskChip) {
            TaskChip.TEAM -> addNewTaskPresenter.addTeamTask(title, description, assignee)
            TaskChip.PERSONAL -> addNewTaskPresenter.addPersonalTask(title, description)
        }
        showToast(Constants.ADDED)
        hideBottomSheet()
    }

    private fun showAssignee() {
        with(binding.root as ViewGroup) {
            transitionVisibility(binding.textviewAddAssigneeName, View.VISIBLE)
            transitionVisibility(binding.editTextAddAssigneeName, View.VISIBLE)
        }
    }

    private fun hideAssignee() {
        with(binding.root as ViewGroup) {
            transitionVisibility(binding.textviewAddAssigneeName, View.GONE)
            transitionVisibility(binding.editTextAddAssigneeName, View.GONE)
        }
    }

    private fun ViewGroup.transitionVisibility(view: View, visibility: Int) {
        if (view.visibility != visibility) {
            TransitionManager.beginDelayedTransition(this )
            view.visibility = visibility
        }
    }

    override fun showTaskAdded(successMessage: String) {
        showToast(successMessage)
    }

    override fun showError(error: IOException) {
        showToast(error)
    }

    private fun hideBottomSheet() {
        dismiss()
    }

    enum class TaskChip {
        PERSONAL, TEAM
    }
}



