package com.tahaproject.todoy_app.ui.register.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tahaproject.todoy_app.BuildConfig
import com.tahaproject.todoy_app.data.models.requests.SignUpRequest
import com.tahaproject.todoy_app.data.models.responses.signupResponse.SignUpResponse
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.register.signup.presenter.SignUpContract
import com.tahaproject.todoy_app.ui.register.signup.presenter.SignUpPresenter
import com.tahaproject.todoy_app.util.SuccessMessage
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class SignUpFragment : BaseFragment<FragmentSignupBinding,SignUpPresenter >(), SignUpContract.View {


    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate
    override val presenter: SignUpPresenter
        get() = SignUpPresenter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCallBacks()
    }
    private fun addCallBacks() {
        binding.textviewLogin.setOnClickListener {
            toLogin()
        }
        binding.buttonSignup.setOnClickListener {
            onSignUp()
        }
    }
    private  fun toLogin(){
        parentFragmentManager.popBackStack()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun onSignUp() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
            presenter.fetchData(username,password,confirmPassword)

    }

    override fun onSuccess(message: String) {
        requireActivity().runOnUiThread {
            toLogin()
            showToast(message)
        }
    }

    override fun onFailedRequest(error: IOException) {
        requireActivity().runOnUiThread {
            error.localizedMessage?.let {
                showToast(it)
            }
        }
    }

    override fun showInvalidUserNameMessage(message: String) {
        requireActivity().runOnUiThread {
            binding.editTextUsername.error =message
        }
    }

    override fun showInvalidPasswordMessage(message: String) {
        requireActivity().runOnUiThread {
            binding.editTextPassword.error =message
        }
    }

    override fun showNotMatchPasswordMessage(message: String) {
        requireActivity().runOnUiThread {
            binding.editTextConfirmPassword.error =message
        }
    }
}