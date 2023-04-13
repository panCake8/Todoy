package com.tahaproject.todoy_app.ui.home.Presenter

import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApiRequest
import com.tahaproject.todoy_app.data.domain.responses.PersonalTodosResponse

class HomePresenter : HomeContract.Presenter {

    private val todoRequest = PersonalTodoApiRequest()
    private var view: HomeContract.View? = null

    override fun fetchData(personTodoRequest: PersonalTodosResponse) {
        view?.let { view ->
            todoRequest.getPersonalTodos({ personalResponse ->
                view.showData(personalResponse)
            }, { ioException ->
                view.showError(ioException)
            }
            )

        }
    }

    override fun attach(view: HomeContract.View) {
        this.view=view
    }

    override fun deAttach() {
    }

}