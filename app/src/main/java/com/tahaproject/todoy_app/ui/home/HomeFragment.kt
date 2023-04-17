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
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(), IHomeContract.IView {

    private lateinit var personalTodosResponse: ToDosResponse
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val getPieChartDataList: List<PieEntry> = listOf(
        PieEntry(15f, "Done"),
        PieEntry(60f, "In progress"),
        PieEntry(35f, "Todo")
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
//            transitionTo(
//                true,
//                R.id.fragment_home_container,
//                SearchFragment(),
//                SearchFragment::class.java.name
//            )
        }

        binding.cardViewRecently.setOnClickListener {
//            transitionTo(
//                true,
//                R.id.fragment_home_container,
//                DetailsTodoFragment(),
//                DetailsTodoFragment::class.java.name
//            )
        }

        binding.editTextSearch.setOnClickListener {
//            transitionTo(
//                true,
//                R.id.fragment_home_container,
//                SearchFragment(),
//                SearchFragment::class.java.name
//            )
        }

        binding.cardViewRecently.setOnClickListener {
//            transitionTo(
//                true,
//                R.id.fragment_home_container,
//                DetailsTodoFragment(),
//                DetailsTodoFragment::class.java.name
//            )
        }

        binding.addFAB.setOnClickListener {
            AddNewTaskFragment().show(parentFragmentManager, NEW_TASK_TAG)
        }
    }

    private fun transitionTo(
        b: Boolean,
        fragmentHomeContainer: Int = R.id.fragment_register_container,
        detailsTodoFragment:Fragment,
        name: String,
    ) {

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



    override fun showTeamToDoData(teamTodoResponse:ToDosResponse) {
        requireActivity().runOnUiThread {



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
            personalTodosResponse = personalTodoResponse
            binding.textViewRecentlyTitle.text = personalTodoResponse.value.last().title
            binding.textViewRecentlyBody.text = personalTodoResponse.value.last().description
            binding.recentlyCardTime.text = personalTodoResponse.value.last().creationTime
        }

    }

    override fun showError(ioException: IOException) {
        requireActivity().runOnUiThread {
            ioException.localizedMessage?.let { showToast(it) }
        }

    }



    companion object {
        private val LABELS_COLORS = listOf(
            Color.parseColor("#00B4D8"),
            Color.parseColor("#03045E"),
            Color.parseColor("#0077B6")
        )
        const val NEW_TASK_TAG = "newTaskTag"
    }

    override val presenter: HomePresenter
        get() = HomePresenter(this, "")
}
