package com.tahaproject.todoy_app.ui.search.presenter

import com.tahaproject.todoy_app.data.requests.SignUpRequest


class SearchPresenter (private val view: ISearchContract.IView):ISearchContract.IPresenter{
    override fun fetchData(signUpRequest: SignUpRequest) {
        TODO("Not yet implemented")
    }
}