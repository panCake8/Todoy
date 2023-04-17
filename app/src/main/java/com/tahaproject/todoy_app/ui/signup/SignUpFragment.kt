package com.tahaproject.todoy_app.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.login.LoginFragment
import com.tahaproject.todoy_app.ui.signup.presenter.SignUpContract
import com.tahaproject.todoy_app.ui.signup.presenter.SignUpPresenter
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class SignUpFragment : BaseFragment<FragmentSignupBinding,SignUpPresenter>(), SignUpContract.View {

    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        presenter.attach(this)
    }

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

    }

    private fun onSignUp() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (!isUsernameValid(username)) {
            binding.editTextUsername.error = "Username should be at least 4 characters."
        } else if (!isPasswordValid(password)) {
            binding.editTextPassword.error =
                "Password should be at least 8 characters and contain at least one lowercase and one uppercase letter."
        } else if (!isPasswordMatch(password, confirmPassword)) {
            binding.editTextConfirmPassword.error = "Passwords do not match."
        } else {
            presenter.fetchData(SignUpRequest(username, password, BuildConfig.teamID))
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

    override fun showData(signUpResponse: SignUpResponse) {
        requireActivity().runOnUiThread {
            if (signUpResponse.isSuccess) {
                parentFragmentManager.popBackStack()
                navigateToLoginScreen()
            } else showToast(ALREADY_USED)
        }
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { showToast(it) }
        }
    }

    private fun navigateToLoginScreen() {
        parentFragmentManager.popBackStack()
//        transitionTo(
//            false,
//            R.id.fragment_register_container,
//            LoginFragment(),
//            LoginFragment::class.java.name
//        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deAttach()
    }

    companion object {
        const val SHORT_NAME = 4
        const val SHORT_PASSWORD = 8
        const val ALREADY_USED = "Already Used!"
    }

    override val presenter: SignUpPresenter
        get() = SignUpPresenter()
}

