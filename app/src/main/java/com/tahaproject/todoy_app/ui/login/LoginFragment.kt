package com.tahaproject.todoy_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.login.presenter.ILoginContract
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter

class LoginFragment: BaseFragmentWithTransition<FragmentLoginBinding>() ,ILoginContract.View{
    private val presenter =LoginPresenter()

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()

    }
    private fun setup(){

        presenter.attach(this)
    }
    private fun addCallback() {

        val username = binding.editTextUsername.text
        val password = binding.editTextPassword.text

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}