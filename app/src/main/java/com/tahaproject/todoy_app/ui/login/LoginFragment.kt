package com.tahaproject.todoy_app.ui.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.requests.LoginRequest
import com.tahaproject.todoy_app.data.responses.LoginResponse
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.HomeActivity
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.login.presenter.ILoginContract
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class LoginFragment : BaseFragment<LoginPresenter, FragmentLoginBinding>(),
    ILoginContract.ILoginView {
    override val presenter: LoginPresenter
        get() = LoginPresenter(this)
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.loginButton.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (!validateUserName(username))
                binding.editTextUsername.error = "You have to enter Your user name"
            else if (!validatePassword(password))
                binding.editTextPassword.error = "You have to enter Password"
            else login(username, password)
        }
        binding.textviewSignUp.setOnClickListener {
//            transitionTo(
//                true,
//                R.id.fragment_register_container,
//                SignUpFragment(),
//                SignUpFragment::class.java.name
//            )
        }
    }

    private fun validateUserName(username: String) = username.isNotEmpty()
    private fun validatePassword(password: String) = password.isNotEmpty()
    private fun login(username: String, password: String) {
        presenter.fetchData(LoginRequest(username, password))
    }

    override fun showData(loginResponse: LoginResponse) {
        requireActivity().runOnUiThread {
            if (loginResponse.isSuccess) {
                SharedPreferenceUtil(requireContext()).saveToken(loginResponse.value.token)
                parentFragmentManager.popBackStack()
                goToHome()
            } else
                showToast(WRONG_USER)
        }
    }

    private fun goToHome() {
        val intent = Intent(requireActivity(), HomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { Log.i("TAG", it) }
        }
    }

    companion object {
        const val WRONG_USER = "Wrong User"
    }
}