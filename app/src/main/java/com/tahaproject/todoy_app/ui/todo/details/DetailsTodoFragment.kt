package com.tahaproject.todoy_app.ui.todo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragment
import com.tahaproject.todoy_app.ui.search.presenter.DeatilsPresenter

class DetailsTodoFragment(override val presenter: DeatilsPresenter) : BaseFragment<DeatilsPresenter,FragmentDetailsBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.appBarDetails.setNavigationOnClickListener {

        }
    }
}