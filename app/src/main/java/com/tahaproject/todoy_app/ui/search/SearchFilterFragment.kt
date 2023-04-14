package com.tahaproject.todoy_app.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentSearchFilterBinding
import com.tahaproject.todoy_app.ui.baseview.BaseBottomSheetDialogFragment


class SearchFilterFragment : BaseBottomSheetDialogFragment<FragmentSearchFilterBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchFilterBinding
        get() = FragmentSearchFilterBinding::inflate

    override fun getLayoutResourceId(): Int = R.layout.fragment_search_filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}