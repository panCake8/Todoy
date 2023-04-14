package com.tahaproject.todoy_app.ui.login


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.domain.requests.LoginRequest
import com.tahaproject.todoy_app.data.domain.responses.LoginResponse
import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition
import com.tahaproject.todoy_app.ui.login.presenter.LoginContract
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter
import com.tahaproject.todoy_app.ui.signup.SignUpFragment
import java.io.IOException

class LoginFragment : BaseFragmentWithTransition<FragmentLoginBinding>(), LoginContract.View {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenter = LoginPresenter()
        loginPresenter.attach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }

    private fun addCallBacks() {
        binding.loginButton.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            loginPresenter.fetchData(LoginRequest(username, password))
//            val intent = Intent(requireActivity(), HomeActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
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

    override fun showData(contentLoginResponse: LoginResponse) {
        requireActivity().runOnUiThread {
            Log.i("TAG", contentLoginResponse.isSuccess.toString())
        }
    }

    override fun showError(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let { Log.i("TAG", it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.deAttach()
    }
}