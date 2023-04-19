package com.tahaproject.todoy_app.ui.todo.team

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamTodoContract
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    ITeamTodoContract.IView {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var teamtodoAdapter: TeamAdapter
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    override fun setup() {

        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
    }

    override fun addCallBack() {


    }

    override val presenter: TeamTodoPresenter
        get() = TeamTodoPresenter(this)

    override fun showTodos(toDosResponse: ToDosResponse) {
        initView(toDosResponse)

    }


    override fun showError(ioException: IOException) {
        requireActivity().runOnUiThread {
            showToast(ioException.localizedMessage.toString())
        }
    }


    private fun initView(toDosResponse: ToDosResponse) {

        teamtodoAdapter = TeamAdapter(toDosResponse.value)
        binding.recyclerTeamTodo.adapter = teamtodoAdapter


    }
}