package plaque.mhealth.ui.login

import android.content.Intent
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_login.*
import plaque.mhealth.commons.snackbar
import plaque.mhealth.model.Login

import plaque.mhealth.R
import plaque.mhealth.database.entities.UserEntity
import plaque.mhealth.ui.main_screen.MainActivity
import plaque.mhealth.ui.BaseActivity
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import plaque.mhealth.services.FallMonitorService
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginView {

    @Inject lateinit var loginPresenter: LoginPresenter
    @Inject lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mHealthApp.loginComponent.inject(this)
        startService(Intent(this, FallMonitorService::class.java))
        login.setOnClickListener{ _ -> showMainActivity()}

        realmTest()
    }

    private fun realmTest() {

        val user1 = User("sin1111@interia.eu", "Szymon", "Makakus", arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf())
        val user2 = User("haikubee95@gmail.com", "Mekeke", "Palony", arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf(), arrayListOf())

        val user1Entity = user1.toUserEntity()
        val user2Entity = user2.toUserEntity()
        realm.beginTransaction()
        realm.copyToRealm(user1Entity)
        realm.copyToRealm(user2Entity)
        realm.commitTransaction()
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

    override fun onDestroy() {
        this.closeRealm()
    }
}


