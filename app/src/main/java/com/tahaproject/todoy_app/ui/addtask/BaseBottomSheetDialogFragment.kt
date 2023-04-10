package com.tahaproject.todoy_app.ui.addtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewBindingDelegate = ViewBindingDelegate { inflater ->
        createBinding(inflater, null)
    }

    val binding: ViewBinding by viewBindingDelegate

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding

    override fun onDestroyView() {
        super.onDestroyView()
        viewBindingDelegate.clearBinding()
    }
}
