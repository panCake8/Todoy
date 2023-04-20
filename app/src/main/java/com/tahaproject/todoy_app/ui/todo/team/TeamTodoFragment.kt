package com.tahaproject.todoy_app.ui.todo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.home.HomeActivity
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamTodoContract
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException



class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    ITeamTodoContract.IView {



    override val presenter: TeamTodoPresenter
        get() = TeamTodoPresenter(this, SharedPreferenceUtil(activity as HomeActivity).getToken())

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    private var selectedTaskChip:TaskChip = TaskChip.TODO

    private lateinit var toDosResponse: ToDosResponse
    private lateinit var adapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }


    override fun setup() {
        chooseGroup()
        setChipClickListeners()
        searchTeam()
        presenter.fetchData()
    }

    override fun addCallBack() {
        binding.appBarTeamTodo.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    private fun searchTeam(){
        binding.searchBar.addTextChangedListener {
            getTeamTodoData()
        }
    }
    private fun getTeamTodoData(){
       val filterList: List<Todo> = toDosResponse.value.filter {
            it.title == Constants.Todo.TITLE
                    &&
                    it.description == Constants.Todo.DESCRIPTION
                    &&
                    it.assignee == Constants.Todo.ASSIGNEE
        }
        adapter = TeamAdapter(filterList)
        binding.recyclerviewTeamTodo.adapter = adapter
    }

    private fun chooseGroup() {
        binding.chipGroupTeamTodo.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_todo -> TaskChip.TODO
                R.id.chip_inProgress -> TaskChip.IN_PROGRESS
                R.id.chip_done -> TaskChip.DONE
                else -> TaskChip.TODO
            }
        }
    }

    private fun setChipClickListeners() {
        binding.chipTodo.setOnClickListener { onChipTodoClicked() }
        binding.chipInProgress.setOnClickListener { onChipInProgressClicked() }
        binding.chipDone.setOnClickListener { onChipDoneClicked() }
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

        adapter = TeamAdapter(filteredList)
        binding.recyclerviewTeamTodo.adapter = adapter
    }

    override fun showTodos(toDosResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            this.toDosResponse = toDosResponse
            adapter = TeamAdapter(toDosResponse.value)
            binding.recyclerviewTeamTodo.adapter = adapter
        }

    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { showToast(it) }
        }
    }

    enum class TaskChip {
        TODO, IN_PROGRESS, DONE
    }
}