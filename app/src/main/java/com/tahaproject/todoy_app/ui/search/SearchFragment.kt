package com.tahaproject.todoy_app.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentSearchBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition

class SearchFragment:BaseFragmentWithTransition<FragmentSearchBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate
}