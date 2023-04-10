package com.tahaproject.todoy_app.ui.addtask

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.FragmentHome
import com.tahaproject.todoy_app.ui.baseview.BaseFragment


class AddNewTaskFragment : BaseBottomSheetDialogFragment() {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return FragmentAddNewTaskBinding.inflate(inflater, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}