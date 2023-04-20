package com.tahaproject.todoy_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.airbnb.lottie.LottieDrawable
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentHomeBinding
import com.tahaproject.todoy_app.ui.addtask.AddNewTaskFragment
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.home.homePresenter.HomeContract
import com.tahaproject.todoy_app.ui.home.homePresenter.HomePresenter
import com.tahaproject.todoy_app.ui.home.PieChart.PieChartHelper
import com.tahaproject.todoy_app.ui.details.DetailsTodoFragment
import com.tahaproject.todoy_app.ui.todo.personal.PersonalTodoFragment
import com.tahaproject.todoy_app.ui.todo.team.TeamTodoFragment
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
        setup()
        addCallBacks()
    }

    private fun setup() {
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
        binding.rootCard.setOnClickListener {
            transitionTo(
                DetailsTodoFragment.newInstance(personalTodo),
                DetailsTodoFragment::class.java.name
            )
        }

        binding.addFAB.setOnClickListener {
            AddNewTaskFragment().show(parentFragmentManager, NEW_TASK_TAG)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            setup()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


    // transition between fragments
    private fun transitionTo(fragment: Fragment, name: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_home_container, fragment, name)
            addToBackStack(HomeFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }


    override fun showPersonalToDoData(personalTodoResponse: ToDosResponse) {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            personalTodo = personalTodoResponse.value.last()
            allTodos.addAll(personalTodoResponse.value)
            if (allTodos.isNotEmpty()) {
                binding.cardViewRecently.visibility = View.VISIBLE
                binding.textViewRecentlyTitle.text = personalTodoResponse.value.last().title
                binding.textViewRecentlyBody.text = personalTodoResponse.value.last().description
                binding.recentlyCardTime.text = personalTodoResponse.value.last().creationTime

                binding.personalTasksLeft.text = personalTodoResponse.value.size.toString()
            } else
                binding.cardViewRecently.visibility = View.GONE
        }
    }

    override fun showTeamToDoData(teamTodoResponse: ToDosResponse) {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            allTodos.addAll(teamTodoResponse.value)
            if (allTodos.isNotEmpty()) {
                allTodos.addAll(teamTodoResponse.value)
                binding.teamTasksLeft.text = teamTodoResponse.value.size.toString()
            }
        }
    }

    override fun showError(ioException: IOException) {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            allTodos = mutableListOf()
            ioException.localizedMessage?.let { showToast(it) }
        }
    }

    override fun showLoading() {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            binding.progressBar.visibility = View.VISIBLE
            binding.viewTextStatistics.visibility = View.INVISIBLE
            binding.viewTextCategory.visibility = View.INVISIBLE
            binding.pieChart.visibility = View.INVISIBLE
            binding.personalCard.visibility = View.INVISIBLE
            binding.teamCard.visibility = View.INVISIBLE
            binding.recently.visibility = View.INVISIBLE
        }
    }

    override fun hideLoading() {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            binding.progressBar.visibility = View.GONE
            binding.viewTextStatistics.visibility = View.VISIBLE
            binding.viewTextCategory.visibility = View.VISIBLE
            binding.pieChart.visibility = View.VISIBLE
            binding.personalCard.visibility = View.VISIBLE
            binding.teamCard.visibility = View.VISIBLE
            binding.recently.visibility = View.VISIBLE
        }
    }

    override fun showAnimation() {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            binding.cardViewRecently.visibility = View.GONE
            binding.lottie.apply {
                visibility = View.VISIBLE
                setAnimation(R.raw.pointer_add_task)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
            }
        }

    }

    override fun hideAnimation() {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            binding.cardViewRecently.visibility = View.VISIBLE
            binding.lottie.visibility = View.GONE
        }
    }

    override fun showChart() {
        pieChartHelper = PieChartHelper(allTodos)
        renderPieChart()
    }

    private fun renderPieChart() {
        if (!isAdded || viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
                .not()
        ) return
        requireActivity().runOnUiThread {
            binding.pieChart.renderPieChartData(pieChartHelper.pieChartDataList)
        }
    }

    companion object {
        const val NEW_TASK_TAG = "new_task_tag"
    }
}
