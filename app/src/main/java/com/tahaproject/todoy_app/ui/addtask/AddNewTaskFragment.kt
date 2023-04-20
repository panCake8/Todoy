package com.tahaproject.todoy_app.ui.addtask


import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.addtask.presenter.IAddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter
import com.tahaproject.todoy_app.ui.base.BaseBottomSheetDialogFragment
import com.tahaproject.todoy_app.ui.home.HomeFragment
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class AddNewTaskFragment :
    BaseBottomSheetDialogFragment<FragmentAddNewTaskBinding, AddNewTaskPresenter>(),
    IAddNewTaskContract.View {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddNewTaskBinding
        get() = FragmentAddNewTaskBinding::inflate

    private val sharedPreferenceUtil by lazy {
        SharedPreferenceUtil(requireContext())
    }

    // get the token
    override val addNewTaskPresenter: AddNewTaskPresenter by lazy { AddNewTaskPresenter(this) }


    private var selectedTaskChip: TaskChip = TaskChip.PERSONAL

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_task

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewTaskPresenter.initApis(sharedPreferenceUtil.getToken())
        addCallback()
    }

    private fun addCallback() {
        binding.buttonPersonalTodo.setOnClickListener { onButtonPersonalTodoClicked() }
        binding.buttonTeamTodo.setOnClickListener { onButtonTeamTodoClicked() }
        binding.buttonAdd.setOnClickListener { onButtonAddClicked() }
    }

    private fun onButtonPersonalTodoClicked() {
        selectedTaskChip = TaskChip.PERSONAL
        binding.buttonPersonalTodo.isSelected = true
        binding.buttonTeamTodo.isSelected = false
        hideAssignee()
    }

    private fun onButtonTeamTodoClicked() {
        selectedTaskChip = TaskChip.TEAM
        binding.buttonPersonalTodo.isSelected = false
        binding.buttonTeamTodo.isSelected = true
        showAssignee()
    }

    private fun onButtonAddClicked() {
        val title = binding.editTextAddTaskTitle.text.toString()
        val description = binding.editTextAddTaskDescription.text.toString()
        val assignee = binding.editTextAddAssigneeName.text.toString()

        when (selectedTaskChip) {
            TaskChip.TEAM -> addNewTaskPresenter.addTeamTask(title, description, assignee)
            TaskChip.PERSONAL -> addNewTaskPresenter.addPersonalTask(title, description)
        }
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
            TransitionManager.beginDelayedTransition(this)
            view.visibility = visibility
        }
    }

    override fun showTaskAdded(successMessage: String) {
        requireActivity().runOnUiThread {
            showToast(successMessage)
            hideBottomSheet()
        }

    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            showToast(error)
        }
    }

    override fun showInvalidTitleMassage(titleMessage: String) {
        requireActivity().runOnUiThread {
            showToast(titleMessage)
        }
    }

    override fun showInvalidDescriptionMassage(descriptionMessage: String) {
        requireActivity().runOnUiThread {
            showToast(descriptionMessage)
        }
    }

    override fun showInvalidAssigneeMassage(assigneeMessage: String) {
        requireActivity().runOnUiThread {
            showToast(assigneeMessage)
        }
    }

    private fun hideBottomSheet() {
        reloadHomeData()
        dismiss()
    }

    private fun reloadHomeData() {
        parentFragmentManager.commit {
            replace(R.id.fragment_home_container, HomeFragment(), HomeFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }

    enum class TaskChip {
        PERSONAL, TEAM
    }
}



