package com.tahaproject.todoy_app.ui.todo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapterListener
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoContract
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class TeamTodoFragment : BaseFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    TeamTodoContract.IView, TeamAdapterListener {

    override val presenter: TeamTodoPresenter by lazy { TeamTodoPresenter(this) }
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    private var selectedTaskChip: TaskChip = TaskChip.TODO

    private lateinit var toDosResponse: ToDosResponse
    private lateinit var adapter: TeamAdapter
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    private fun setup() {
        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
        presenter.token = sharedPreferenceUtil.getToken()
        presenter.fetchData()
    }

    private fun addCallBack() {
        binding.chipTodo.setOnClickListener {
            onChipTodoClicked()
        }
        binding.chipInProgress.setOnClickListener {
            onChipInProgressClicked()
        }
        binding.chipDone.setOnClickListener {
            onChipDoneClicked()
        }
        binding.appBarTeamTodo.setNavigationOnClickListener {
            back()
        }
        binding.chipGroupTeamTodo.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_todo -> TaskChip.TODO
                R.id.chip_inProgress -> TaskChip.IN_PROGRESS
                R.id.chip_done -> TaskChip.DONE
                else -> TaskChip.TODO //Default
            }
        }
        binding.searchBar.addTextChangedListener {
            adapter.filter(it.toString())
        }
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun onChipTodoClicked() {
        processClickedChipData(toDosResponse, TaskChip.TODO)
    }

    private fun onChipInProgressClicked() {
        processClickedChipData(toDosResponse, TaskChip.IN_PROGRESS)
    }

    private fun onChipDoneClicked() {
        processClickedChipData(toDosResponse, TaskChip.DONE)
    }

    private fun processClickedChipData(toDosResponse: ToDosResponse, status: TaskChip) {
        selectedTaskChip = status
        val filteredList: List<Todo> = when (status) {
            TaskChip.TODO -> toDosResponse.value.filter { it.status == Constants.TODO_STATUS }
            TaskChip.IN_PROGRESS -> toDosResponse.value.filter { it.status == Constants.IN_PROGRESS_STATUS }
            TaskChip.DONE -> toDosResponse.value.filter { it.status == Constants.DONE_STATUS }
        }
        adapter = TeamAdapter(filteredList, this)
        binding.recyclerviewTeamTodo.adapter = adapter
    }

    override fun showTodos(toDosResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            this.toDosResponse = toDosResponse
            initViews(toDosResponse)
        }
    }

    private fun initViews(toDosResponse: ToDosResponse) {
        if (toDosResponse.isSuccess) {
            adapter = TeamAdapter(toDosResponse.value, this)
            binding.recyclerviewTeamTodo.adapter = adapter
        }
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { showToast(it) }
        }
    }

    override fun showLoading() {
        requireActivity().runOnUiThread {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        requireActivity().runOnUiThread {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClickItem(item: Todo) {
        parentFragmentManager.commit {
            replace(
                R.id.fragment_home_container,
                DetailsTodoFragment.newInstance(item),
                DetailsTodoFragment::class.java.name
            )
            addToBackStack(TeamTodoFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }

    enum class TaskChip {
        TODO, IN_PROGRESS, DONE
    }

}


