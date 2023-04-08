package com.tahaproject.todoy_app.ui


import android.view.LayoutInflater
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate


    override fun onBackPressed() {
        super.onBackPressed()
    }
}