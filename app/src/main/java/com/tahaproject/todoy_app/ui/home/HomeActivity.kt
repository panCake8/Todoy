package com.tahaproject.todoy_app.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import com.tahaproject.todoy_app.R
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.base.BaseActivity
import com.tahaproject.todoy_app.ui.home.activityPresenter.ActivityPresenter
import com.tahaproject.todoy_app.ui.home.activityPresenter.ActivityContract
import com.tahaproject.todoy_app.ui.register.RegisterActivity
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeActivity : BaseActivity<ActivityHomeBinding, ActivityPresenter>(),
    ActivityContract.IView {
    override val presenter: ActivityPresenter by lazy { ActivityPresenter(this) }
    override val bindingInflate: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate
    private lateinit var personalTodoResponse: ToDosResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setUp()
        addCallBack()
    }

    private fun setUp() {
        val token = SharedPreferenceUtil(this).getToken()
        presenter.token = token
        presenter.fetchPersonalData()
    }

    private fun addCallBack() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            presenter.fetchPersonalData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun showPersonalToDoData(personalTodoResponse: ToDosResponse) {
        this.personalTodoResponse = personalTodoResponse
    }

    override fun navigateToLoginScreen() {
        runOnUiThread {
            hideErrorImage()
            val intent = Intent(this@HomeActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun navigateToHomeScreen() {
        runOnUiThread {
            hideErrorImage()
            supportFragmentManager.commit {
                replace(R.id.fragment_home_container, HomeFragment(), HomeFragment::class.java.name)
                setReorderingAllowed(true)
            }

        }
    }

    override fun showErrorImage() {
        runOnUiThread {
            binding.fragmentHomeContainer.visibility = View.GONE
            binding.imgNoInternet.visibility = View.VISIBLE
        }

    }

    override fun hideErrorImage() {
        runOnUiThread {
            binding.fragmentHomeContainer.visibility = View.VISIBLE
            binding.imgNoInternet.visibility = View.GONE
        }

    }

    override fun noInternet() {
        showErrorImage()
    }

    override fun showError(ioException: IOException) {
        runOnUiThread {
            ioException.localizedMessage?.let { showToast(it) }
        }
    }

    override fun serverError() {
        showErrorImage()
    }


}