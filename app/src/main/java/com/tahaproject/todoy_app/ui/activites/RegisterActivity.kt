package com.tahaproject.todoy_app.ui.activites


import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tahaproject.todoy_app.databinding.ActivityRegisterBinding
import com.tahaproject.todoy_app.ui.baseview.BaseActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override val bindingInflate: (LayoutInflater) -> ActivityRegisterBinding
        get() = ActivityRegisterBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setup()
    }

    private fun setup() {

    }

}