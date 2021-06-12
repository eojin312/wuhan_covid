package assignment.wuhan_covid.senario.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import assignment.wuhan_covid.R
import assignment.wuhan_covid.senario.main.MainActivity
import com.autocrypt.mi.green.dvr.utils.LogHelper.LogHelper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.getCovidList(this)
        splashViewModel.loadCovidList(this)
        initObserver()
    }
    private val splashViewModel : SplashViewModel by viewModels()

    private fun initObserver() {
        splashViewModel.isProgress.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                LogHelper.showToast("뭔가 문제가 있는 것 같은데요.")
                return@observe
            }
        }
    }


}