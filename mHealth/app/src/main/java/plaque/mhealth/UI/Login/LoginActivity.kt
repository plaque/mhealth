package plaque.mhealth.UI.Login

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import plaque.mhealth.Commons.snackbar
import plaque.mhealth.Model.Login

import plaque.mhealth.R
import plaque.mhealth.UI.Android.Activities.MainActivity
import plaque.mhealth.UI.BaseActivity
import plaque.mhealth.mHealthApp
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginView {

    @Inject lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mHealthApp.loginComponent.inject(this)
        login.setOnClickListener{ _ -> login()}
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


