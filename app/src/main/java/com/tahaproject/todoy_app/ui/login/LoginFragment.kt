package com.tahaproject.todoy_app.ui.login


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.HomeActivity
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.login.presenter.LoginContract
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPresenter>(),
    LoginContract.IView {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val presenter: LoginPresenter
        get() = LoginPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.loginButton.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            presenter.validateUserName(username)
            presenter.validateUserName(password)
            login(username,password)
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
                // TODO see if this is correct when make it empty if null
                SharedPreferenceUtil(requireContext()).saveToken(loginResponse.value.token ?: "")
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

    override fun showMessage(message: String) {
        binding.editTextUsername.error = message
    }

    companion object {
        const val WRONG_USER = "Wrong User"
    }


}