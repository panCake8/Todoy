package com.tahaproject.todoy_app.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.FakeDataManager
import com.tahaproject.todoy_app.data.models.responses.PersonalTodosResponse
import com.tahaproject.todoy_app.data.models.responses.TeamToDosResponse
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.activities.presenter.HomeContract
import com.tahaproject.todoy_app.ui.activities.presenter.HomePresenter
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.search.SearchFragment
import com.tahaproject.todoy_app.ui.todo.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.PersonalTodoFragment
import com.tahaproject.todoy_app.ui.todo.team.TeamTodoFragment
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.CustomPercentFormatter
import java.io.IOException


class HomeFragment : BaseFragmentWithTransition<FragmentHomeBinding>(), HomeContract.HomeView {
    private lateinit var presenter: HomePresenter
    val fakeDataManager = FakeDataManager()
    private lateinit var personalTodosResponse: PersonalTodosResponse.PersonalTodo
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        personalTodosResponse =
//            arguments?.getParcelable(Constants.Home)!!
    }


    private val getPieChartDataList: List<PieEntry> = listOf(
        PieEntry(getDonePercentage(), Constants.DONE_STRING),
        PieEntry(getInProgressPercentage(), Constants.IN_PROGRESS_STRING),
        PieEntry(getTodoPercentage(), Constants.TODO_STRING)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        renderPieChart(binding.pieChart)
        setListeners(binding)
    }

    private fun setListeners(binding: FragmentHomeBinding) {
        binding.viewAllTeam.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                TeamTodoFragment(),
                TeamTodoFragment::class.java.name
            )
        }

        binding.viewAllPersonal.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                PersonalTodoFragment(),
                PersonalTodoFragment::class.java.name
            )

        }

        binding.editTextSearch.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                SearchFragment(),
                SearchFragment::class.java.name
            )
        }

        binding.cardViewRecently.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                DetailsTodoFragment(),
                DetailsTodoFragment::class.java.name
            )
        }

        binding.editTextSearch.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                SearchFragment(),
                SearchFragment::class.java.name
            )
        }

        binding.cardViewRecently.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_home_container,
                DetailsTodoFragment(),
                DetailsTodoFragment::class.java.name
            )
        }

        binding.addFAB.setOnClickListener {
            AddNewTaskFragment().show(parentFragmentManager, NEW_TASK_TAG)
        }
    }

    private fun renderPieChart(pieChart: PieChart) {
        setPieChartDesign(pieChart)
        val dataSet = PieDataSet(getPieChartDataList, Constants.EMPTY_STRING)
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

    fun showData(personalResponse: PersonalTodosResponse.PersonalTodo?) {
        requireActivity().runOnUiThread {
//            binding.textViewRecentlyTitle.text = personalResponse?.title
//            binding.textViewRecentlyBody.text = personalResponse?.description
//            binding.recentlyCardTime.text = personalResponse?.creationTime
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deAttach()
    }

    private fun getTaskStatusCount(status: Int): Int =
        makeAllTodosList(fakeDataManager).count { (it as? PersonalTodosResponse.PersonalTodo)?.status == status || (it as? TeamToDosResponse.TeamToDo)?.status == status }

    private fun getTodoCount(): Int = getTaskStatusCount(Constants.TODO_STATUS)
    private fun getTodoPercentage() = getTodoCount().todoPercentage(makeAllTodosList(fakeDataManager).size)

    private fun getDoneCount(): Int = getTaskStatusCount(Constants.DONE_STATUS)
    private fun getDonePercentage() = getDoneCount().todoPercentage(makeAllTodosList(fakeDataManager).size)

    private fun getInProgressCount(): Int = getTaskStatusCount(Constants.IN_PROGRESS_STATUS)
    private fun getInProgressPercentage() =
        getInProgressCount().todoPercentage(makeAllTodosList(fakeDataManager).size)

    private fun Int.todoPercentage(totalCount: Int) =
        (this.toFloat() / totalCount.toFloat()) * Constants.ONE_HUNDRED_PERCENT

    private fun makeAllTodosList(fakeDataManager: FakeDataManager) =
        fakeDataManager.personalTodosList + fakeDataManager.teamToDosList

    companion object {
        private val LABELS_COLORS = listOf(
            Color.parseColor("#00B4D8"),
            Color.parseColor("#03045E"),
            Color.parseColor("#0077B6")
        )
        const val NEW_TASK_TAG = "newTaskTag"

        fun newInstance(personalTodosResponse: PersonalTodosResponse.PersonalTodo) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.Home, personalTodosResponse)
                }
            }
    }

    override fun navigateToLoginScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToHomeScreen() {
        TODO("Not yet implemented")
    }

    override fun showError(ioException: IOException) {
        TODO("Not yet implemented")
    }
}
