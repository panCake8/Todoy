package com.tahaproject.todoy_app.ui.home

import android.view.View
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding

fun FragmentHomeBinding.toggleProgressBarVisibility(show: Boolean) {
    val visibility = if (show) View.VISIBLE else View.GONE
    progressBar.visibility = visibility
}

fun FragmentHomeBinding.toggleHomeViewsVisibility(show: Boolean) {
    val visibility = if (show) View.VISIBLE else View.GONE
    viewTextStatistics.visibility = visibility
    viewTextCategory.visibility = visibility
    pieChart.visibility = visibility
    personalCard.visibility = visibility
    teamCard.visibility = visibility
    recently.visibility = visibility
    cardViewRecently.visibility = visibility
}

fun FragmentHomeBinding.updateUI(personalTodoResponse: ToDosResponse) {
    // Implement UI update logic here
}