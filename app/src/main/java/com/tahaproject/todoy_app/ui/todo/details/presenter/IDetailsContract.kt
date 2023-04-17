package com.tahaproject.todoy_app.ui.search.presenter

import java.io.IOException

interface IDetailsContract {
    interface IDetailsView {
        fun showData()
        fun showError(error: IOException)
    }

    interface IDetailsPresenter {
        fun fetchData()
    }
}