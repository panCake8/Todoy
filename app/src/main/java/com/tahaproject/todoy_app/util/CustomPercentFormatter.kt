package com.tahaproject.todoy_app.util

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.formatter.PercentFormatter

class CustomPercentFormatter(pieChart: PieChart) : PercentFormatter(pieChart) {
    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()} %"
    }
}
