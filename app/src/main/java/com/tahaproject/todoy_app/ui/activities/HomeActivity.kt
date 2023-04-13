package com.tahaproject.todoy_app.ui


import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.baseview.BaseActivity
import com.tahaproject.todoy_app.ui.home.HomeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setUp() {
        supportFragmentManager.commit {
            replace(R.id.fragment_home_container, HomeFragment(), HomeFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }
}