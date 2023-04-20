package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.adapter.PersonalAdapter
import com.tahaproject.todoy_app.ui.todo.personal.adapter.PersonalAdapterListener
import com.tahaproject.todoy_app.ui.todo.personal.presenter.PersonalTodoContract
import com.tahaproject.todoy_app.ui.todo.personal.presenter.PersonalTodoPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class PersonalTodoFragment : BaseFragment<FragmentPersonalTodoBinding, PersonalTodoPresenter>(),
    PersonalTodoContract.IView, PersonalAdapterListener {

    private var selectedTaskChip: TaskChip = TaskChip.TODO
    private lateinit var adapter: PersonalAdapter
    private lateinit var toDosResponses: ToDosResponse

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override val presenter: PersonalTodoPresenter by lazy { PersonalTodoPresenter(this) }

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
        binding.chipGroupPersonalTodo.setOnCheckedStateChangeListener { _, checkedId ->
            selectedTaskChip = when (checkedId[0]) {
                R.id.chip_todo -> TaskChip.TODO
                R.id.chip_inProgress -> TaskChip.IN_PROGRESS
                R.id.chip_done -> TaskChip.DONE
                else -> TaskChip.TODO
            }
        }
        binding.appBarPersonalTodo.setNavigationOnClickListener {
            back()
        }
        binding.chipTodo.setOnClickListener {
            onChipTodoClicked()
        }

        binding.chipInProgress.setOnClickListener {
            onChipInProgressClicked()
        }
        binding.chipDone.setOnClickListener {
            onChipDoneClicked()
        }
        binding.searchBar.addTextChangedListener {
            adapter.filterPersonalTodosBySearch(it.toString())
        }
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun onChipTodoClicked() {
        processClickedChipData(toDosResponses, TaskChip.TODO)
    }

    private fun onChipInProgressClicked() {
        processClickedChipData(toDosResponses, TaskChip.IN_PROGRESS)
    }

    private fun onChipDoneClicked() {
        processClickedChipData(toDosResponses, TaskChip.DONE)
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
            toDosResponses = toDosResponse
            initViews(toDosResponses)
        }
    }

    private fun initViews(toDosResponse: ToDosResponse) {
        if (toDosResponse.isSuccess) {
            adapter = PersonalAdapter(toDosResponse.value, this)
            binding.recyclerPersonalTodo.adapter = adapter
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
            addToBackStack(PersonalTodoFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }

    enum class TaskChip {
        TODO, IN_PROGRESS, DONE
    }
}










