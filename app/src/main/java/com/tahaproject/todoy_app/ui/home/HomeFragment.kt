package com.tahaproject.todoy_app.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.home.homePresenter.HomePresenter
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import com.tahaproject.todoy_app.ui.register.RegisterActivity
import com.tahaproject.todoy_app.ui.search.SearchFragment
import com.tahaproject.todoy_app.ui.todo.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.PersonalTodoFragment
import com.tahaproject.todoy_app.ui.todo.team.TeamTodoFragment
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.CustomPercentFormatter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), IHomeContract.IView {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var allTodos: MutableList<Todo>

    override
    val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        sharedPreferenceUtil = SharedPreferenceUtil(this.requireContext())

        renderPieChart(binding.pieChart)
        setListeners(binding)
    }

    private fun setListeners(binding: FragmentHomeBinding) {
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
            transitionTo(
                SearchFragment(),
                SearchFragment::class.java.name
            )
        }

        binding.cardViewRecently.setOnClickListener {
            transitionTo(
                DetailsTodoFragment(),
                DetailsTodoFragment::class.java.name
            )
        }

        binding.editTextSearch.setOnClickListener {
            transitionTo(
                SearchFragment(),
                SearchFragment::class.java.name
            )
        }

        binding.cardViewRecently.setOnClickListener {
            transitionTo(
                DetailsTodoFragment(),
                DetailsTodoFragment::class.java.name
            )
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
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_home_container, fragment)
        transaction.addToBackStack(name)
            .commit()

    }


    private fun renderPieChart(pieChart: PieChart) {
        setPieChartDesign(pieChart)
        val dataSet = PieDataSet(getPieChartDataList, "")
        pieChart.data = createFormattedPieData(dataSet, pieChart)
        pieChart.invalidate()
        pieChart.animateY(1500, Easing.EaseInOutQuad)
    }

    private fun setPieChartDesign(pieChart: PieChart) {
        pieChart.apply {
            setUsePercentValues(true)
            setEntryLabelTextSize(12f)
            setDrawCenterText(true)
            setEntryLabelColor(Color.WHITE)
            isDrawHoleEnabled = true
            description.isEnabled = false

            setPieChartLegendDesign(legend)
        }

    }

    private fun setPieChartLegendDesign(legend: Legend) {
        legend.isEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.xOffset = 12f
        legend.yOffset = 12f
        legend.orientation = Legend.LegendOrientation.VERTICAL

    }


    private fun createFormattedPieData(dataSet: PieDataSet, pieChart: PieChart): PieData {
        dataSet.colors = LABELS_COLORS
        dataSet.sliceSpace = 5f
        dataSet.selectionShift = 10f
        dataSet.valueTextSize = 12f
        dataSet.valueFormatter = CustomPercentFormatter(pieChart)
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(12f)
        return data
    }


    override fun showTeamToDoData(teamTodoResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            allTodos.addAll(teamTodoResponse.value)
            binding.teamTasksLeft.text = teamTodoResponse.value.count { it.status != 2 }.toString()

        }

    }

    override fun navigateToLoginScreen() {
        parentFragmentManager.popBackStack()
        val intent = Intent(requireActivity(), RegisterActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun navigateToHomeScreen() {
        TODO("Not yet implemented")
    }

    override fun showPersonalToDoData(personalTodoResponse: ToDosResponse) {
        requireActivity().runOnUiThread {
            allTodos.addAll(personalTodoResponse.value)
            binding.textViewRecentlyTitle.text = personalTodoResponse.value.last().title
            binding.textViewRecentlyBody.text = personalTodoResponse.value.last().description
            binding.recentlyCardTime.text = personalTodoResponse.value.last().creationTime
        }

    }

    override fun showError(ioException: IOException) {
        requireActivity().runOnUiThread {
            allTodos = mutableListOf()
            ioException.localizedMessage?.let { showToast(it) }
        }
    }

    private val getPieChartDataList: List<PieEntry> = listOf(
        PieEntry(getDonePercentage(), Constants.DONE_STRING),
        PieEntry(getInProgressPercentage(), Constants.IN_PROGRESS_STRING),
        PieEntry(getTodoPercentage(), Constants.TODO_STRING)
    )

    private fun getTaskStatusCount(status: Int): Int =
        allTodos.count { it.status == status }

    private fun getTodoCount(): Int = getTaskStatusCount(Constants.TODO_STATUS)
    private fun getTodoPercentage() = getTodoCount().todoPercentage(allTodos.size)

    private fun getDoneCount(): Int = getTaskStatusCount(Constants.DONE_STATUS)
    private fun getDonePercentage() = getDoneCount().todoPercentage(allTodos.size)

    private fun getInProgressCount(): Int = getTaskStatusCount(Constants.IN_PROGRESS_STATUS)
    private fun getInProgressPercentage() =
        getInProgressCount().todoPercentage(allTodos.size)

    private fun Int.todoPercentage(totalCount: Int) =
        (this.toFloat() / totalCount.toFloat()) * Constants.ONE_HUNDRED_PERCENT


    companion object {
        private val LABELS_COLORS = listOf(
            Color.parseColor("#00B4D8"),
            Color.parseColor("#03045E"),
            Color.parseColor("#0077B6")
        )
        const val NEW_TASK_TAG = "newTaskTag"
    }

    override val presenter: HomePresenter
        get() = HomePresenter(this, sharedPreferenceUtil.getToken())
}
