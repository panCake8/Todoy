package com.tahaproject.todoy_app.ui


import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.ActivityRegisterBinding
import com.tahaproject.todoy_app.ui.base.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override val bindingInflate: (LayoutInflater) -> ActivityRegisterBinding
        get() = ActivityRegisterBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_register_container,
                LoginFragment(),
                LoginFragment::class.java.name
            )
            setReorderingAllowed(true)
        }
    }

}