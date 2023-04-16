package com.tahaproject.todoy_app.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.onDrawableClickListener
import com.tahaproject.todoy_app.databinding.FragmentSearchBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragment
import com.tahaproject.todoy_app.ui.search.presenter.IPersonalContract
import com.tahaproject.todoy_app.ui.search.presenter.PersonalPresenter
import java.io.IOException

class SearchFragment (override val presenter: PersonalPresenter): BaseFragment<PersonalPresenter,FragmentSearchBinding,>(),IPersonalContract.ISearchView {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBar.setDrawableClickListener(object : onDrawableClickListener {
            override fun onClick(target: DrawablePosition) {
                 if (target == DrawablePosition.RIGHT) {
                     SearchFilterFragment().show(parentFragmentManager, Const.FILTER_TAG)
                 }
            }
        })
    }

    object Const {
        const val FILTER_TAG = "newFilterTag"
    }

    override fun showData() {

    }

    override fun showError(error: IOException) {

    }
}