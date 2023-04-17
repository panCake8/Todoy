package com.tahaproject.todoy_app.ui.todo.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentTeamTodoBinding
import com.tahaproject.todoy_app.ui.search.presenter.TeamPresenter
import com.tahaproject.todoy_app.ui.todo.ToDoFragment


class TeamTodoFragment : ToDoFragment<FragmentTeamTodoBinding,TeamPresenter>() {
    override fun setup() {

    }

    override fun addCallBack() {


    }

    override val presenter: TeamPresenter
        get() = TODO("Not yet implemented")
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTodoBinding
        get() = TODO("Not yet implemented")


}