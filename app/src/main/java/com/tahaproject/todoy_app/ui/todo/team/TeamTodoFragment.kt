package com.tahaproject.todoy_app.ui.todo.team

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.team.presenter.TeamTodoPresenter


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding,TeamTodoPresenter>() {
    override fun setup() {

    }

    override fun addCallBack() {


    }

    override val presenter: TeamTodoPresenter
        get() = TODO("Not yet implemented")
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = TODO("Not yet implemented")

}