package com.tahaproject.todoy_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.activites.HomeActivity
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.signup.SignUpFragment

class LoginFragment : BaseFragmentWithTransition<FragmentLoginBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.loginButton.setOnClickListener {
//            val username = binding.editTextUsername.text.toString()
//            val password = binding.editTextPassword.text.toString()
            //AuthApiRequest(ApiRequest()).login(LoginRequest(username, password))
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.textviewSignUp.setOnClickListener {
            transitionTo(
                true,
                R.id.fragment_register_container,
                SignUpFragment(),
                SignUpFragment::class.java.name
            )
        }
    }

}