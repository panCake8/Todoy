package com.tahaproject.todoy_app.ui.baseview

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.tahaproject.todoy_app.ui.baseview.enum.AddToBackStack

abstract class BaseFragmentWithTransition<VB : ViewBinding> : BaseFragment<VB>() {

    fun transitionTo(
        addToBackStack: AddToBackStack,
        fragmentContainer: Int,
        fragment: Fragment,
        tag: String
    ) {
        when (addToBackStack) {
            AddToBackStack.NO -> transitionWithoutAddToBackStack(fragmentContainer, fragment, tag)
            AddToBackStack.YES -> transitionWithAddToBackStack(fragmentContainer, fragment, tag)
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