package com.tahaproject.todoy_app.ui.register


import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.ActivityRegisterBinding
import com.tahaproject.todoy_app.ui.base.BaseActivity
import com.tahaproject.todoy_app.ui.register.login.LoginFragment
import com.tahaproject.todoy_app.ui.register.registerPresenter.RegisterContract
import com.tahaproject.todoy_app.ui.register.registerPresenter.RegisterPresenter

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterPresenter>(),
    RegisterContract.IView {
    override val bindingInflate: (LayoutInflater) -> ActivityRegisterBinding
        get() = ActivityRegisterBinding::inflate
    override val presenter: RegisterPresenter
        get() = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        presenter.onLoginScreen()
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_register_container,
                LoginFragment(),
                LoginFragment::class.java.name
            )
            setReorderingAllowed(true)
        }
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(LoginFragment::class.java.name) in supportFragmentManager.fragments) {
            supportFragmentManager.popBackStackImmediate()
        }
        super.onBackPressed()

    }


}