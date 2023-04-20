package com.tahaproject.todoy_app.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.home.homePresenter.HomeContract
import com.tahaproject.todoy_app.ui.home.homePresenter.HomePresenter
import com.tahaproject.todoy_app.ui.todo.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.PersonalTodoFragment
import com.tahaproject.todoy_app.ui.todo.team.TeamTodoFragment
import com.tahaproject.todoy_app.util.CustomPercentFormatter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(),
    HomeContract.IView {
    override
    val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override val presenter: HomePresenter by lazy { HomePresenter(this) }

    private lateinit var personalTodo: Todo

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private var allTodos = mutableListOf<Todo>()
    private lateinit var pieChartHelper: PieChartHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareToken()
    }

    private fun prepareToken() {
        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())
        presenter.token = sharedPreferenceUtil.getToken()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChartHelper = PieChartHelper(allTodos)
        setup()
        toggleHomeViewsVisibility(false)
        addCallBacks()
    }

    private fun setup() {
        renderPieChart()
        presenter.fetchPersonalData()
        presenter.fetchTeamData()
    }

    private fun addCallBacks() {
        binding.viewAllTeam.setOnClickListener {
            transitionTo(
                TeamTodoFragment(),
                TeamTodoFragment::class.java.name
            )
        }

        binding.viewAllPersonal.setOnClickListener {
            transitionTo(
                PersonalTodoFragment(),
                PersonalTodoFragment::class.java.name
            )

        }

        binding.editTextSearch.setOnClickListener {
//            transitionTo(
//                SearchFragment(),
//                SearchFragment::class.java.name
//            )
        }
        binding.yassen.setOnClickListener {
            transitionTo(
                DetailsTodoFragment.newInstance(personalTodo),
                DetailsTodoFragment::class.java.name
            )
        }

        binding.editTextSearch.setOnClickListener {
//            transitionTo(
//                SearchFragment(),
//                SearchFragment::class.java.name
//            )
        }

        binding.cardViewRecently.setOnClickListener {
//            transitionTo(
//                DetailsTodoFragment(),
//                DetailsTodoFragment::class.java.name
//            )
        }

        binding.addFAB.setOnClickListener {
            AddNewTaskFragment().show(parentFragmentManager, NEW_TASK_TAG)
        }
    }


    // transition between fragments
    private fun transitionTo(
        fragment: Fragment,
        name: String,
    ) {
        parentFragmentManager.commit {
            replace(R.id.fragment_home_container, fragment, name)
            addToBackStack(name)
            setReorderingAllowed(true)
        }
    }

    private fun renderPieChart() {
        binding.pieChart.renderPieChartData(pieChartHelper.pieChartDataList)
    }

    override fun showPersonalToDoData(personalTodoResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            personalTodo = personalTodoResponse.value.last()
            toggleProgressBarVisibility(false)
            toggleHomeViewsVisibility(true)
            allTodos.addAll(personalTodoResponse.value)
            if (allTodos.isNotEmpty()) {
                binding.textViewRecentlyTitle.text = personalTodoResponse.value.last().title
                binding.textViewRecentlyBody.text = personalTodoResponse.value.last().description
                binding.recentlyCardTime.text = personalTodoResponse.value.last().creationTime
                binding.personalTasksLeft.text = personalTodoResponse.value.size.toString()
            }
        }
    }

    override fun showTeamToDoData(teamTodoResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            if (allTodos.isNotEmpty()) {
                toggleProgressBarVisibility(false)
                toggleHomeViewsVisibility(true)
                allTodos.addAll(teamTodoResponse.value)
                binding.teamTasksLeft.text = teamTodoResponse.value.size.toString()
            }
        }
    }

    override fun showError(ioException: IOException) {
        requireActivity().runOnUiThread {
            toggleProgressBarVisibility(false)
            allTodos = mutableListOf()
            ioException.localizedMessage?.let { showToast(it) }
        }
    }


    private fun toggleProgressBarVisibility(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }

    private fun toggleHomeViewsVisibility(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        binding.viewTextStatistics.visibility = visibility
        binding.viewTextCategory.visibility = visibility
        binding.pieChart.visibility = visibility
        binding.personalCard.visibility = visibility
        binding.teamCard.visibility = visibility
        binding.recently.visibility = visibility
        binding.cardViewRecently.visibility = visibility
    }

    companion object {
        const val NEW_TASK_TAG = "new_task_tag"
    }
}
