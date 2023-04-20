package com.tahaproject.todoy_app.ui.home
// CustomPieChartView.kt
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tahaproject.todoy_app.util.CustomPercentFormatter

class CustomPieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PieChart(context, attrs, defStyleAttr) {

    init {
        setUsePercentValues(true)
        setEntryLabelTextSize(12f)
        setDrawCenterText(true)
        setEntryLabelColor(Color.WHITE)
        isDrawHoleEnabled = true
        description.isEnabled = false
        setPieChartLegendDesign(legend)
    }

    private fun setPieChartLegendDesign(legend: Legend) {
        legend.apply {
            isEnabled = true
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            xOffset = 12f
            yOffset = 12f
            orientation = Legend.LegendOrientation.VERTICAL
        }
    }

    fun renderPieChartData(pieChartDataList: List<PieEntry>) {
        val dataSet = PieDataSet(pieChartDataList, "")
        data = createFormattedPieData(dataSet)
        invalidate()
        animateY(1500, Easing.EaseInOutQuad)
    }

    private fun createFormattedPieData(dataSet: PieDataSet): PieData {
        dataSet.apply {
            colors = LABELS_COLORS
            sliceSpace = 5f
            selectionShift = 10f
            valueTextSize = 12f
            valueFormatter = CustomPercentFormatter(this@CustomPieChartView)
        }
        return PieData(dataSet).apply {
            setDrawValues(true)
            setValueTextColor(Color.WHITE)
            setValueTextSize(12f)
        }
    }

    companion object {
        private val LABELS_COLORS = listOf(
            Color.parseColor("#00B4D8"),
            Color.parseColor("#03045E"),
            Color.parseColor("#0077B6")
        )
    }
}
