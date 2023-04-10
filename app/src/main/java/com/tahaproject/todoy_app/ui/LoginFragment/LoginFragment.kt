package com.tahaproject.todoy_app.ui.LoginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.ApiRequest
import com.tahaproject.todoy_app.data.dataManger.AuthApiRequest
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() =FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClick()

    }

    private fun onClick() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        binding.loginButton.setOnClickListener {
        AuthApiRequest(ApiRequest()).login(LoginRequest(username,password))

        }
    }

    private fun setup() {


    }

}