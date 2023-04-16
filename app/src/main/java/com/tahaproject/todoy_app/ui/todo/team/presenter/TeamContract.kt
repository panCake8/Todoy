package com.tahaproject.todoy_app.ui.search.presenter

import java.io.IOException

interface TeamContract {
    interface IDetailsView {
        fun showData()
        fun showError(error: IOException)
    }

    interface IDetailsPresenter {
        fun fetchData()
    }
}