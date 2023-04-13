package com.tahaproject.todoy_app.ui.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tahaproject.todoy_app.databinding.FragmentAddNewTaskBinding
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskContract
import com.tahaproject.todoy_app.ui.addtask.presenter.AddNewTaskPresenter


class AddNewTaskFragment : BottomSheetDialogFragment(), AddNewTaskContract.View {

    private var _binding: FragmentAddNewTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: AddNewTaskContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize presenter
        presenter = AddNewTaskPresenter(this)
        binding.buttonAdd.setOnClickListener {

        }
        // Set up UI interactions
//        presenter.onAddTaskButtonClick(taskName)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Implement View interface methods
    override fun showTaskAddedSuccessfully() {
        // Show success message to user
    }

    override fun showTaskAdditionFailed() {
        // Show failure message to user
    }
}