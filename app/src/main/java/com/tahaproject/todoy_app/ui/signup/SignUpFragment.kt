package com.tahaproject.todoy_app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.home.HomeFragment
import com.tahaproject.todoy_app.util.showToast

class SignUpFragment : BaseFragmentWithTransition<FragmentSignupBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.textViewLogin.setOnClickListener {
            goToLogin()
        }
        binding.buttonSignup.setOnClickListener {
            onSignUp()
        }
    }

    private fun goToLogin() {
        back()
    }

    private fun goToHome() {
        parentFragmentManager.popBackStack()
        transitionTo(
            false,
            R.id.fragment_register_container,
            HomeFragment(),
            HomeFragment::class.java.name
        )
    }

    private fun onSignUp() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (!isUsernameValid(username)) {
            binding.editTextPassword.error = "Username should be at least 4 characters."
            return
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.error =
                "Password should be at least 8 characters and contain at least one lowercase and one uppercase letter."
            return
        } else if (!isPasswordMatch(password, confirmPassword)) {
            showToast("Passwords do not match.")
            return
        } else {
            goToHome()
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.length >= 4
    }

    private fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z]).+\$")
        return password.length >= 8 && regex.matches(password)
    }

    private fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}

