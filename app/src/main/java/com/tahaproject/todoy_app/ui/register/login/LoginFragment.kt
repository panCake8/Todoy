package com.tahaproject.todoy_app.ui.register.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.home.HomeActivity
import com.tahaproject.todoy_app.ui.register.login.presenter.LoginContract
import com.tahaproject.todoy_app.ui.register.login.presenter.LoginPresenter
import com.tahaproject.todoy_app.ui.register.signup.SignUpFragment
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginPresenter>(), LoginContract.IView {

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
            fetchData()
        }
        binding.textviewSignUp.setOnClickListener {
            goToSignUp()
        }
    }

    private fun fetchData() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        presenter.fetchData(username, password)
    }

    private fun goToSignUp() {
        parentFragmentManager.commit {
            replace(
                R.id.fragment_register_container,
                SignUpFragment(),
                SignUpFragment::class.java.name
            )
            addToBackStack(LoginFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }

    override fun onSuccess() {
        requireActivity().runOnUiThread {
            parentFragmentManager.popBackStackImmediate()
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onFailRequest(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { showToast(it) }
        }
    }

    override fun showInvalidUserNameMassage(userNameMessage: String) {
        requireActivity().runOnUiThread {
            binding.editTextUsername.error = userNameMessage
        }
    }

    override fun showInvalidPasswordMassage(passwordMessage: String) {
        requireActivity().runOnUiThread {
            binding.editTextPassword.error = passwordMessage
        }
    }

    override fun getToken(token: String) {
        requireActivity().runOnUiThread {
            SharedPreferenceUtil(requireContext()).saveToken(token)
        }
    }
}