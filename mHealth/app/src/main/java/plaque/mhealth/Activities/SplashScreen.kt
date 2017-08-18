package plaque.mhealth.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

import plaque.mhealth.R

/**
 * Created by szymon on 18.08.17.
 */

class SplashScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
        {
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {

        // Splash screen timer
        private val SPLASH_TIME_OUT = 3000
    }
}
