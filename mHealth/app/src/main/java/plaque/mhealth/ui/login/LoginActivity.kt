package plaque.mhealth.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import plaque.mhealth.commons.snackbar
import plaque.mhealth.model.Login

import plaque.mhealth.R
import plaque.mhealth.database.RealmService
import plaque.mhealth.ui.main_screen.MainActivity
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Settings
import plaque.mhealth.services.FallMonitorService
import javax.inject.Inject

class LoginActivity: AppCompatActivity(), LoginView {

    @Inject lateinit var loginPresenter: LoginPresenter
    @Inject lateinit var realm: RealmService
    var settings: Settings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mHealthApp.loginComponent.inject(this)

        settings = realm.getSettings()

        login.setOnClickListener{ _ -> login()}

    }

    fun login(){
        //val login = Login("sin1111",  "password")
        var login1: Login?
        if(email.text.toString() == ""){
            login1 = null
        }
        else{
            login1 = Login("sin1111",  "password")
        }
        loginPresenter.onLoginClick(login1)
    }

    override fun onStart() {
        super.onStart()
        loginPresenter.setView(this)
    }

    override fun onStop() {
        super.onStop()
        loginPresenter.clearView()
    }

    override fun showMainActivity() {
        if(settings?.fallMonitoring ?: false)
            startService(Intent(this, FallMonitorService::class.java))

        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showErrorMessage(message: String) {
        login_activity_layout.snackbar(message)
    }

}


