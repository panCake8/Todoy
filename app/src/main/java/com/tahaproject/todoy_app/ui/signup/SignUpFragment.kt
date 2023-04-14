package com.tahaproject.todoy_app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition

class SignUpFragment : BaseFragmentWithTransition<FragmentSignupBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.textviewLogin.setOnClickListener {
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
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
//        val api = AuthApiRequest(ApiRequest(),this).register(RegisterRequest(username, password))
//        parentFragmentManager.popBackStack(LoginFragment::class.java.name, POP_BACK_STACK_INCLUSIVE)
//        val intent = Intent(requireActivity(), HomeActivity::class.java)
//        startActivity(intent)
//        requireActivity().finish()
    }

    private fun onSignUp() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (!isUsernameValid(username)) {
            binding.editTextUsername.error = "Username should be at least 4 characters."
            return
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.error =
                "Password should be at least 8 characters and contain at least one lowercase and one uppercase letter."
            return
        } else if (!isPasswordMatch(password, confirmPassword)) {
            binding.editTextConfirmPassword.error = "Passwords do not match."
            return
        } else {
            goToHome()
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.length >= SHORT_NAME
    }

    private fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z]).+\$")
        return password.length >= SHORT_PASSWORD && regex.matches(password)
    }

    private fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    companion object {
        const val SHORT_NAME = 4
        const val SHORT_PASSWORD = 8
    }
}

