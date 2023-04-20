package com.tahaproject.todoy_app.ui.home


import com.github.mikephil.charting.data.PieEntry
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.todoPercentage

class PieChartHelper(private val allTodos: List<Todo>) {

    val pieChartDataList: List<PieEntry>
        get() = listOf(
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
    private fun getInProgressPercentage() = getInProgressCount().todoPercentage(allTodos.size)
}
