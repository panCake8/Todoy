package com.tahaproject.todoy_app.ui.todo.team

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.home.HomeActivity
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.ui.todo.team.presenter.ITeamTodoContract
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import java.io.IOException


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding, TeamTodoPresenter>(),
    ITeamTodoContract.IView {

    private lateinit var adapter: TeamAdapter
    override fun setup() {

    }

    override fun addCallBack() {


    }

    override val presenter: TeamTodoPresenter
        get() = TeamTodoPresenter(this, SharedPreferenceUtil(activity as HomeActivity).getToken())
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = FragmentTeamTodoBinding::inflate

    override fun showTodos(toDosResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            adapter = TeamAdapter(toDosResponse.value)
            binding.recyclerviewTeamTodo.adapter = adapter
        }
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "${error.message}", Toast.LENGTH_SHORT).show()
        }
    }

}