package com.tahaproject.todoy_app.ui.todo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.todo.details.presenter.IDetailsContract
import com.tahaproject.todoy_app.ui.todo.details.presenter.IDetailsPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import okio.IOException

class DetailsTodoFragment :
    BaseFragment<FragmentDetailsBinding, IDetailsPresenter>(), IDetailsContract.IView {
    lateinit var updateTodoTask: UpdateTodoTask
    private val sharedPreferenceUtil by lazy {
        SharedPreferenceUtil(requireContext())
    }
    override val presenter: IDetailsPresenter
        get() = IDetailsPresenter(this, sharedPreferenceUtil.getToken())

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasKDetails = arguments?.getParcelable(Constants.TASK_DETAILS) as Todo?
        updateTodoTask = UpdateTodoTask(tasKDetails!!.id, tasKDetails.status)
        viewDetails(tasKDetails)
        addCallBacks()
    }

    private fun addCallBacks() {
        onBack()
        binding.button.setOnClickListener {
            onClickButton()
        }
    }

    private fun onBack() {
        binding.appBarDetails.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun onClickButton() {
        if (updateTodoTask.status == 0) {
            updateTodoTask.status = 1
            onUpdate(IN_PROGRESS, true)
            presenter.updatePersonalTodoTask(UpdateTodoTask(updateTodoTask.id, 1))
        } else {
            updateTodoTask.status = 2
            onUpdate(DONE, false)
            presenter.updatePersonalTodoTask(UpdateTodoTask(updateTodoTask.id, 2))
        }
    }

    private fun viewDetails(tasKDetails: Todo?) {
        binding.textViewTaskTitle.text = tasKDetails?.title
        binding.textViewTaskDescription.text = tasKDetails?.description
        checkStatus(tasKDetails)
        if (tasKDetails?.assignee == "")
            binding.chipMemberName.visibility = View.GONE
        else
            binding.chipMemberName.text = tasKDetails?.assignee

    }

    private fun checkStatus(tasKDetails: Todo?) {
        when (tasKDetails?.status) {
            0 -> onChangeStatus(TODO, START_TASK, true)
            1 -> onChangeStatus(IN_PROGRESS, DONE, true)
            2 -> onChangeStatus(DONE, "", false)
        }
    }

    private fun onChangeStatus(status: String, buttonText: String, show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.textViewTaskStats.text = status
        binding.button.text = buttonText
        binding.button.visibility = visibility
    }

    private fun onUpdate(stats: String, show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.button.text = DONE
        binding.textViewTaskStats.text = stats
        binding.button.visibility = visibility
    }

    override fun showTaskUpdated(successMessage: String) {
        requireActivity().runOnUiThread {
            showToast(successMessage)
        }

    }

    override fun showError(error: IOException) {
        showToast(error)
    }

    companion object {
        fun newInstance(tasKDetails: Todo) =
            DetailsTodoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.TASK_DETAILS, tasKDetails)
                }
            }

        const val DONE = "Done"
        const val IN_PROGRESS = "In Progress"
        const val TODO = "Todo"
        const val START_TASK = "Start_Task"
    }
}
