package com.tahaproject.todoy_app.ui.todo.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.FragmentPersonalTodoBinding
import com.tahaproject.todoy_app.ui.todo.ToDoFragment
import com.tahaproject.todoy_app.ui.todo.personal.adapter.PersonalAdapter
import com.tahaproject.todoy_app.ui.todo.personal.presenter.IPersonalTodoContract
import com.tahaproject.todoy_app.ui.todo.personal.presenter.PersonalTodoPresenter
import com.tahaproject.todoy_app.ui.todo.team.adapter.TeamAdapter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException


class PersonalTodoFragment : ToDoFragment<FragmentPersonalTodoBinding, PersonalTodoPresenter>(),
    IPersonalTodoContract.IView {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
private lateinit var personalAdapter : PersonalAdapter
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding
        get() = FragmentPersonalTodoBinding::inflate

    override fun setup() {
        sharedPreferenceUtil = SharedPreferenceUtil(requireContext())

    }

    override fun addCallBack() {

    }

    override val presenter: PersonalTodoPresenter
        get() = PersonalTodoPresenter(this)

    override fun showTodos(toDosResponse: ToDosResponse) {
        initview(toDosResponse)

    }

    private fun initview(toDosResponse: ToDosResponse) {
        personalAdapter  = PersonalAdapter(toDosResponse.value)
        binding.recyclerPersonalTodo.adapter = personalAdapter


    }

    override fun showError(error: IOException) {
        showToast(error.localizedMessage.toString())
    }


}