package com.tahaproject.todoy_app.ui.todo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.todo.details.presenter.IDetailsPresenter

class DetailsTodoFragment(override val presenter: IDetailsPresenter) : BaseFragment<FragmentDetailsBinding,IDetailsPresenter>() {
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
    fun newInstance(tasKDetails: Todo) =
        DetailsTodoFragment(IDetailsPresenter()).apply {
            arguments = Bundle().apply {
                putParcelable("task details", tasKDetails)
            }
        }


}