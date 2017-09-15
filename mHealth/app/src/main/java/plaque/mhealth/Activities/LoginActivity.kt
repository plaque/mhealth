package plaque.mhealth.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import plaque.mhealth.Model.Login

import plaque.mhealth.R
import plaque.mhealth.Retrofit.LoginRestAPI
import plaque.mhealth.mHealthApp
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var api: LoginRestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHealthApp.loginComponent.inject(this)
        setContentView(R.layout.activity_login)

        login.setOnClickListener{ _ -> getToken()}
    }

    fun getToken(){

        val login:Login = Login(email.text.toString(), password.text.toString())

        api.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            response -> println(response)
                            api.user().subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        user -> println(user)
                                        startActivity(Intent(this, TasksActivity::class.java))
                                    })
                        },
                        {
                            e -> println(e.message)
                        }
                )


    }
}
