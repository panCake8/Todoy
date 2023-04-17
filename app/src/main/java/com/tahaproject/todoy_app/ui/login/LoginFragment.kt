package com.tahaproject.todoy_app.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import com.tahaproject.todoy_app.databinding.FragmentLoginBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.login.presenter.LoginPresenter

class LoginFragment: BaseFragment<FragmentLoginBinding,LoginPresenter>(){


    override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val presenter: LoginPresenter
        get() = TODO("Not yet implemented")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            onLogin()

    }
     private fun onLogin() {
//         val username = binding.editTextUsername.text.toString()
//         val password = binding.editTextPassword.text.toString()


//       presenter.fetchData(LoginRequest(username,password))
     }

//    override fun onSuccess(loginResponse: LoginResponse) {
//        if (loginResponse.isSuccess){
//
//        }
//        else showToast(INCORRECT_INPUT)
//    }
//
//    override fun onFailRequest(error: IOException) {
//        requireActivity().runOnUiThread {
//            error.localizedMessage?.let { showToast(it) }
//        }
//    }
//    override fun showInvalidMassage(usernameMassage:String,passwordMassage:String) {
//        binding.editTextUsername.error =usernameMassage
//        binding.editTextPassword.error =passwordMassage
//    }

    companion object {

        const val INCORRECT_INPUT = "your username or password is incorrect"
    }


}