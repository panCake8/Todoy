package com.tahaproject.todoy_app.ui.register.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.requests.LoginRequest
import com.tahaproject.todoy_app.data.models.responses.loginResponse.LoginResponse
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.home.HomeFragment
import com.tahaproject.todoy_app.ui.login.presenter.LoginContract
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter
import com.tahaproject.todoy_app.ui.register.signup.SignUpFragment
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPresenter>(), LoginContract.IView {


    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val presenter: LoginPresenter
        get() = TODO("Not yet implemented")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClickLoginButton()
        goToSignUp()

    }

    private fun onClickLoginButton() {

        binding.loginButton.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            presenter.fetchData(LoginRequest(username, password))
        }
    }
    private fun goToSignUp(){
        binding.textviewSignUp.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_register_container, SignUpFragment())
                setReorderingAllowed(true)
            }
        }
    }

    override fun onSuccess(loginResponse: LoginResponse) {
        requireActivity().runOnUiThread {
            if (loginResponse.isSuccess) {

                parentFragmentManager.commit {
                    replace(R.id.fragment_home_container, HomeFragment())
                    setReorderingAllowed(true)
                }

            } else showToast(SUCCESS_LOGIN)
        }
    }

    override fun onFailRequest(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { showToast(it) }
        }
    }

    override fun showInvalidMassage(usernameMassage: String, passwordMassage: String) {
        binding.editTextUsername.error = usernameMassage
        binding.editTextPassword.error = passwordMassage
    }

    override fun getToken(token: String?) {
       SharedPreferenceUtil(requireContext()).saveToken(token!!)
    }

    companion object {
        const val SIGNUP = "signup"
        const val SUCCESS_LOGIN = "success login"

    }
}