package com.tahaproject.todoy_app.ui

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
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition


class FragmentHome : BaseFragmentWithTransition<FragmentHomeBinding>() {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderPieChart(binding.pieChart)
    }

    private fun renderPieChart(pieChart: PieChart) {
        setPieChartDesign(pieChart)
        val dataSet = PieDataSet(getPieChartDataList(), "")
        pieChart.data = getFormattedDataSet(dataSet, pieChart)
        pieChart.invalidate()
        pieChart.animateY(1500, Easing.EaseInOutQuad)
    }

    private fun getPieChartDataList() = listOf(
        PieEntry(15f, "Done"),
        PieEntry(60f, "In progress"),
        PieEntry(35f, "Todo")
    )

    private fun setPieChartDesign(pieChart: PieChart) {
        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            legend.isEnabled = true
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            legend.xOffset = 12f
            legend.yOffset = 12f
            legend.orientation = Legend.LegendOrientation.VERTICAL
            setEntryLabelTextSize(12f)
        }

    }


    private fun getFormattedDataSet(dataSet: PieDataSet, pieChart: PieChart): PieData {
        dataSet.colors = listOf(
            Color.parseColor("#00B4D8"),
            Color.parseColor("#03045E"),
            Color.parseColor("#0077B6")
        )
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


}

class CustomPercentFormatter(pieChart: PieChart) : PercentFormatter(pieChart) {
    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()} %"
    }
}

