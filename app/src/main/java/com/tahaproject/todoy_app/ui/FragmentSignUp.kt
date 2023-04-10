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
        addCallBacks()
    }
    
    private fun addCallBacks(){
     binding.textViewLogin.setOnClickListener {
            goToLogin()
        }
         binding.buttonSignup.setOnClickListener {
         onSignUp()
         }
    }
    
    private  fun goToLogin(){
       //TODO:
    }
    private  fun onSignUp(){
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if (!isUsernameValid(username)) {
            showToast("Username should be at least 4 characters.")
            return
        }
        else if (!isPasswordValid(password)) {
            showToast("Password should be at least 8 characters and contain at least one lowercase and one uppercase letter.")
            return
        }
        else if (!isPasswordMatch(password, confirmPassword)) {
            showToast("Passwords do not match.")
            return
        }else{
           // TODO:
        }
    }
    private fun isUsernameValid(username: String): Boolean {
        return username.length >= 4
    }

    private fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z]).+\$")
        return password.length >= 8 && regex.matches(password)
    }

    private fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

