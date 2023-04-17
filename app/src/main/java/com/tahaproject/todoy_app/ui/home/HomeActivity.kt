package com.tahaproject.todoy_app.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.ToDosResponse
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.ActivityHomeBinding
import com.tahaproject.todoy_app.ui.base.BaseActivity
import com.tahaproject.todoy_app.ui.home.homePresenter.HomePresenter
import com.tahaproject.todoy_app.ui.presenter.IHomeContract
import com.tahaproject.todoy_app.ui.register.RegisterActivity
import com.tahaproject.todoy_app.util.showToast
import java.io.IOException

class HomeActivity : BaseActivity<ActivityHomeBinding, HomePresenter>(), IHomeContract.IView {

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
        presenter.fetchTeamData()
        presenter.fetchPersonalData()
    }





    override fun showPersonalToDoData(personalTodoResponse: ToDosResponse) {
        TODO("Not yet implemented")
    }

    override fun showTeamToDoData(teamTodoResponse: ToDosResponse) {
        TODO("Not yet implemented")
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToHomeScreen() {
//        supportFragmentManager.commit {
//            replace(R.id.fragment_home_container, HomeFragment(), HomeFragment::class.java.name)
//            setReorderingAllowed(true)
//        }
    }

    override fun showError(ioException: IOException) {
        runOnUiThread {
            ioException.localizedMessage?.let { showToast(it) }
        }
    }


    override val presenter: HomePresenter
        get() = HomePresenter(this,"")
}