package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.home.HomeActivity
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.adapter.PersonalAdapter
import com.tahaproject.todoy_app.ui.todo.personal.adapter.PersonalAdapterListener
import com.tahaproject.todoy_app.ui.todo.personal.presenter.IPersonalTodoContract
import com.tahaproject.todoy_app.ui.todo.personal.presenter.PersonalTodoPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class PersonalTodoFragment : ToDoFragment<FragmentPersonalTodoBinding, PersonalTodoPresenter>(),
    IPersonalTodoContract.IView, PersonalAdapterListener {

    private var selectedTaskChip: TaskChip = TaskChip.TODO
    private lateinit var adapter: PersonalAdapter
    private lateinit var toDosResponse: ToDosResponse

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override val presenter: PersonalTodoPresenter
        get() = PersonalTodoPresenter(
            this,
            SharedPreferenceUtil(activity as HomeActivity).getToken()
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        addCallBack()
    }

    override fun setup() {
        chooseGroup()
        setChipClickListeners()
        presenter.fetchData()
    }

    override fun addCallBack() {
        binding.appBarPersonalTodo.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun chooseGroup() {
        binding.chipGroupPersonalTodo.setOnCheckedStateChangeListener { _, checkedId ->
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

        adapter = PersonalAdapter(filteredList, this)
        binding.recyclerPersonalTodo.adapter = adapter
    }

    override fun showTodos(toDosResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            this.toDosResponse = toDosResponse
            adapter = PersonalAdapter(toDosResponse.value, this)
            binding.recyclerPersonalTodo.adapter = adapter
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

    override fun onClickItem(item: Todo) {
        parentFragmentManager.commit {
            replace(
                R.id.fragment_home_container,
                DetailsTodoFragment.newInstance(item),
                DetailsTodoFragment::class.java.name
            )
            addToBackStack(PersonalTodoFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }
}










