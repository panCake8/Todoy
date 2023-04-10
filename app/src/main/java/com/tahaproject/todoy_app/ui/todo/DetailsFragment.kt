package com.tahaproject.todoy_app.ui.todo


import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragmentWithTransition<FragmentDetailsBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

}