package com.tahaproject.todoy_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.FragmentSignupBinding
import com.tahaproject.todoy_app.ui.baseview.BaseFragment
import com.tahaproject.todoy_app.ui.baseview.BaseFragmentWithTransition

class FragmentSignUp:BaseFragmentWithTransition<FragmentSignupBinding>() {
    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding
        get() = FragmentSignupBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToLogin()
        onSignUp()

    }
    private  fun goToLogin(){
        binding.textViewLogin.setOnClickListener {
        }
    }
    private  fun onSignUp(){
        binding.buttonSignup.setOnClickListener {
            validateInput()
        }
    }


    private fun validateInput() {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z]).+\$")
        if (binding.editTextUsername.length() < 4) {
            Toast.makeText(
                requireContext(),
                "username should be 4 characters at least",
                Toast.LENGTH_LONG
            ).show()
        }
        if (binding.editTextPassword.length() < 8) {
            Toast.makeText(
                requireContext(),
                "password should be 8 characters at least",
                Toast.LENGTH_LONG
            ).show()
        }
        if (!regex.matches(binding.editTextPassword.toString())) {
            Toast.makeText(
                requireContext(),
                "password should content at least one letter small and capital",
                Toast.LENGTH_LONG
            ).show()
        }
        if (binding.editTextPassword != binding.editTextConfirmPassword) {
            Toast.makeText(
                requireContext(),
                "password not match",
                Toast.LENGTH_LONG
            ).show()
        }
    }


}