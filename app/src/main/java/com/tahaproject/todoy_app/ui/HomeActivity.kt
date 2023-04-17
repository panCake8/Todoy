package com.tahaproject.todoy_app.ui


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.presenter.HomePresenter
import com.tahaproject.todoy_app.ui.base.BaseActivity
import com.tahaproject.todoy_app.ui.home.HomeFragment
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeActivity : BaseActivity<ActivityHomeBinding,HomePresenter>() {

    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setUp() {
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun navigateToHomeScreen() {
        supportFragmentManager.commit {
            replace(R.id.fragment_home_container, HomeFragment(), HomeFragment::class.java.name)
            setReorderingAllowed(true)
        }
    }

    override fun showError(ioException: IOException) {
        runOnUiThread {
            ioException.localizedMessage?.let { showToast(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deAttach()
    }

    override val presenter: HomePresenter
        get() = HomePresenter(this)
}