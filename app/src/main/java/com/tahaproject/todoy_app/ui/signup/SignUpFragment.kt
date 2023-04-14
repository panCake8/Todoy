package com.tahaproject.todoy_app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.domain.requests.SignUpRequest
import com.tahaproject.todoy_app.data.domain.responses.SignUpResponse
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.signup.presenter.SignupContract
import com.tahaproject.todoy_app.ui.signup.presenter.SignupPresenter
import com.tahaproject.todoy_app.util.ErrorMessage
import com.tahaproject.todoy_app.util.SuccessMessage
import java.io.IOException

class SignUpFragment : BaseFragmentWithTransition<FragmentSignupBinding>(), SignupContract.View {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate
    private lateinit var signupPresenter: SignupPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupPresenter = SignupPresenter(this)
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
    private fun onSignUp() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (!isUsernameValid(username)) {
            binding.editTextUsername.error = ErrorMessage.SHORT_USERNAME
            return
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.error =ErrorMessage.PASSWORD_SHORT
            return
        } else if (!isPasswordMatch(password, confirmPassword)) {
            binding.editTextConfirmPassword.error = ErrorMessage.PASSWORD_NOT_MATCH
            return
        } else {
            signupPresenter.fetchData(
                SignUpRequest(
                    username,
                    password,
                    BuildConfig.teamID
                )
            )
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return username.length >= SHORT_NAME
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= SHORT_PASSWORD
    }

    private fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    companion object {
        const val SHORT_NAME = 4
        const val SHORT_PASSWORD = 8
    }

    override fun showData(signUpResponse: SignUpResponse) {
        requireActivity().runOnUiThread {
            showToast(SuccessMessage.SIGNUP_SUCCESSFULLY)
            goToLogin()
        }
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            showToast(error.message!!)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

