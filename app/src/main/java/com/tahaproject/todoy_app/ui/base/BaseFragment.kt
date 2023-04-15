package com.tahaproject.todoy_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, T> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    abstract val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract val presenter: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflate(layoutInflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cleanupBindings()
    }

    protected open fun cleanupBindings() {
        _binding = null
    }
}