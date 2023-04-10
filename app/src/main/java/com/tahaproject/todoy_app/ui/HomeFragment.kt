package com.tahaproject.todoy_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.todo.PersonalTodoFragment
import com.tahaproject.todoy_app.ui.todo.TeamTodoFragment

class HomeFragment : BaseFragmentWithTransition<FragmentHomeBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCallBacks()

    }

    private fun addCallBacks() {
        binding.viewAllTeam.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_container,
                TeamTodoFragment(),
                TeamTodoFragment::class.java.name
            )
        }

        binding.viewAllPersonal.setOnClickListener {

            transitionTo(
                true,
                R.id.fragment_container,
                PersonalTodoFragment(),
                PersonalTodoFragment::class.java.name
            )

        }

    }
}