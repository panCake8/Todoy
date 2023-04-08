package com.tahaproject.todoy_app.ui.baseview

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding


abstract class BaseFragmentWithTransition<VB : ViewBinding> : BaseFragment<VB>() {

    fun transitionTo(
        addToBackStack: Boolean,
        fragmentContainer: Int,
        fragment: Fragment,
        tag: String
    ) {
        when (addToBackStack) {
            false -> transitionWithoutAddToBackStack(fragmentContainer, fragment, tag)
            true -> transitionWithAddToBackStack(fragmentContainer, fragment, tag)
        }
    }

    fun back() {
        requireActivity().onBackPressed()
    }

    private fun transitionWithAddToBackStack(
        fragmentContainer: Int,
        fragment: Fragment,
        tag: String
    ) {
        parentFragmentManager.commit {
            replace(fragmentContainer, fragment, tag)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    private fun transitionWithoutAddToBackStack(
        fragmentContainer: Int,
        fragment: Fragment,
        tag: String
    ) {
        parentFragmentManager.commit {
            replace(fragmentContainer, fragment, tag)
            setReorderingAllowed(true)
        }
    }
}