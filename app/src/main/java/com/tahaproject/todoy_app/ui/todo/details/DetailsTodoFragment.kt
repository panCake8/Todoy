package com.tahaproject.todoy_app.ui.todo.details


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.tahaproject.todoy_app.data.models.TaskDetails
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.home.HomeFragment
import com.tahaproject.todoy_app.util.Constants

class DetailsTodoFragment : BaseFragmentWithTransition<FragmentDetailsBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.appBarDetails.setNavigationOnClickListener {
            back()
        }
    }

    companion object {
        fun newInstance(taskDetails: TaskDetails): DetailsTodoFragment =
            DetailsTodoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.DETAILS, taskDetails)
                }
            }

    }
}
