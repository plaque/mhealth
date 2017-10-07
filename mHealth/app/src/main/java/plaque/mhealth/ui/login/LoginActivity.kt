package plaque.mhealth.ui.login

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import plaque.mhealth.commons.snackbar
import plaque.mhealth.model.Login

import plaque.mhealth.R
import plaque.mhealth.ui.main_screen.MainActivity
import plaque.mhealth.ui.BaseActivity
import plaque.mhealth.mHealthApp
import plaque.mhealth.services.FallMonitorService
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginView {

    @Inject lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mHealthApp.loginComponent.inject(this)
        startService(Intent(this, FallMonitorService::class.java))
        login.setOnClickListener{ _ -> showMainActivity()}
    }

    fun login(){
        val login = Login(email.text.toString(),  password.text.toString())
        loginPresenter.onLoginClick(login)
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.setView(this)
    }

    override fun onStop() {
        super.onStop()
        loginPresenter.clearView()
    }

    override fun closeRealm() {
        loginPresenter.closeRealm()
    }

    override fun showMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showErrorMessage(message: String) {
        login_activity_layout.snackbar(message)
    }

}


