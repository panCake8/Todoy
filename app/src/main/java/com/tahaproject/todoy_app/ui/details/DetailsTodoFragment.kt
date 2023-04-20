package com.tahaproject.todoy_app.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.details.presenter.DetailsContract
import com.tahaproject.todoy_app.ui.details.presenter.DetailsPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.Constants.DONE_STATUS
import com.tahaproject.todoy_app.util.Constants.IN_PROGRESS_STATUS
import com.tahaproject.todoy_app.util.Constants.TODO_STATUS
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import okio.IOException

class DetailsTodoFragment :
    BaseFragment<FragmentDetailsBinding, DetailsPresenter>(), DetailsContract.IView {
    private lateinit var updateTodoTask: UpdateTodoTask
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override val presenter: DetailsPresenter by lazy { DetailsPresenter(this) }

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasKDetails = arguments?.getParcelable(Constants.TASK_DETAILS) as Todo?
        updateTodoTask = UpdateTodoTask(tasKDetails!!.id, tasKDetails.status)
        setup()
        viewDetails(tasKDetails)
        addCallBacks()
    }

    private fun setup() {
        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
        presenter.initApis(sharedPreferenceUtil.getToken())
    }

    private fun addCallBacks() {
        binding.appBarDetails.setNavigationOnClickListener {
            back()
        }
        binding.button.setOnClickListener {
            onClickButton()
        }
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun onClickButton() {
        if (updateTodoTask.status == TODO_STATUS) {
            updateTodoTask.status = IN_PROGRESS_STATUS
            onUpdate(IN_PROGRESS, true)
            if (binding.textViewAssignName.text == "")
                presenter.updatePersonalTodoTask(
                    UpdateTodoTask(
                        updateTodoTask.id,
                        IN_PROGRESS_STATUS
                    )
                )
            else
                presenter.updateTeamTodoTask(UpdateTodoTask(updateTodoTask.id, IN_PROGRESS_STATUS))
        } else {
            updateTodoTask.status = DONE_STATUS
            onUpdate(DONE, false)
            if (binding.textViewAssignName.text == Constants.PERSONAL)
                presenter.updatePersonalTodoTask(UpdateTodoTask(updateTodoTask.id, DONE_STATUS))
            else
                presenter.updateTeamTodoTask(UpdateTodoTask(updateTodoTask.id, DONE_STATUS))
        }
    }

    private fun viewDetails(tasKDetails: Todo) {
        binding.textViewTaskTime.text= tasKDetails.creationTime.substring(11,16)
        binding.textViewTaskDate.text= tasKDetails.creationTime.substring(0,10)
        binding.textViewTaskTitle.text = tasKDetails.title
        binding.textViewTaskDescription.text = tasKDetails.description
        checkStatus(tasKDetails)
        if (tasKDetails.assignee == Constants.PERSONAL)
            binding.textViewAssignName.visibility = View.GONE
        else
            binding.textViewAssignName.text = tasKDetails.assignee

    }

    private fun checkStatus(tasKDetails: Todo?) {
        when (tasKDetails?.status) {
            TODO_STATUS -> onChangeStatus(TODO, START_TASK, true)
            IN_PROGRESS_STATUS -> onChangeStatus(IN_PROGRESS, DONE, true)
            DONE_STATUS -> onChangeStatus(DONE, "", false)
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
        requireActivity().runOnUiThread {
            showToast(error)
        }

    }
    private fun showErrorImage() {
        binding.fragmentDetailsContainer.visibility = View.GONE
        binding.imgNoInternet.visibility = View.VISIBLE
    }

    override fun noInternet() {
        showErrorImage()
    }

    override fun serverError() {
        showErrorImage()
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

        const val DONE_STATUS = 2
        const val IN_PROGRESS_STATUS = 1
        const val TODO_STATUS = 0
    }
}
