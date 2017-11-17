package plaque.mhealth.ui.activities

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fall_detected_activity.*
import org.jetbrains.anko.sendSMS
import plaque.mhealth.R
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.retrofit.TokenInterceptor
import plaque.mhealth.retrofit.UserRestAPI
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

/**
 * Created by szymon on 07.10.17.
 */
class FallDetectedActivity: AppCompatActivity() {

    @Inject lateinit var api: UserRestAPI
    @Inject lateinit var realmService: RealmService
    @Inject lateinit var loginInterceptor: TokenInterceptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fall_detected_activity)

        mHealthApp.fallComponent.inject(this)
        sendNotificationWithDelay()
        loginInterceptor.sessionToken = realmService.getToken()

        warning_img.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dismiss_button.setOnClickListener{ _ -> finish()}
    }
    
    private fun sendNotificationWithDelay()
            = Handler().postDelayed({ sendNotification() }, 5 * 1000)

    
    private fun sendNotification(){
        sendMail()
        sendSms()
    }

    private fun sendMail(){
        api.sendMail().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            _ -> fall_detected.append("\nMail sent!")
                        },
                        {
                            e -> print(e.message)
                        }
                )
    }

    private fun sendSms(){
        fall_detected.append("\nSms sent!")

    }
}
