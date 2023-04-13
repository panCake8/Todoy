package com.tahaproject.todoy_app.ui.home.Presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse
import java.io.IOException

interface HomeContract {

    interface View {
        fun showData(personalTodosResponse: PersonalTodosResponse)
        fun showError(error: IOException)
    }

    interface Presenter {
        fun fetchData(personTodoRequest: PersonalTodosResponse)
        fun attach(view: View)
        fun deAttach()
    }
}