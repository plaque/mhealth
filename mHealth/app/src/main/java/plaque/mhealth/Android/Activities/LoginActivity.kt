package plaque.mhealth.Android.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import plaque.mhealth.Commons.snackbar
import plaque.mhealth.Model.Login

import plaque.mhealth.R
import plaque.mhealth.Retrofit.LoginRestAPI
import plaque.mhealth.mHealthApp
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var api: LoginRestAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mHealthApp.loginComponent.inject(this)

        login.setOnClickListener{ _ -> startActivity(Intent(this, MainActivity::class.java))}
    }

    fun getToken(){

        val login:Login = Login("admin", "password")

        api.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                           response -> manageResponse(response)
                        },
                        {
                            login_activity_layout.snackbar("Check your internet connection.")
                        }
                )
    }

    private fun manageResponse(response: retrofit2.Response<ResponseBody>): Unit{
        when(response.code()){
            401 -> login_activity_layout.snackbar("Bad credentials. Check your login and password.")
            200 -> startActivity(Intent(this, MainActivity::class.java))
            500, 501, 502, 503, 504 -> login_activity_layout.snackbar("Server internal error.")
        }
    }
}


