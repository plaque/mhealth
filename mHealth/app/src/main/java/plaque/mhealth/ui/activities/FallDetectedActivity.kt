package plaque.mhealth.ui.activities

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.telephony.SmsManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fall_detected_activity.*
import org.jetbrains.anko.sendSMS
import plaque.mhealth.R
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Settings
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
    var settings: Settings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fall_detected_activity)

        mHealthApp.fallComponent.inject(this)

        settings = realmService.getSettings()

        loginInterceptor.sessionToken = realmService.getToken()

        val handler = sendNotificationWithDelay()

        warning_img.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dismiss_button.setOnClickListener{ _ -> callOff(handler)}
    }

    private fun callOff(handler: Handler){
        handler.removeCallbacks({ sendNotification() })
        finish()
    }
    
    private fun sendNotificationWithDelay(): Handler{
        val handler = Handler()
        handler.postDelayed({ sendNotification() }, 5 * 1000)

        return handler
    }

    
    private fun sendNotification(){
        if(settings?.emails ?: false) sendMail()
        if(settings?.sms ?: false) sendSms()
    }

    private fun sendMail(){
        api.sendMail().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            _ -> fall_detected.append(resources.getString(R.string.email_sent))
                        },
                        {
                            e -> print(e.message)
                        }
                )
    }

    private fun sendSms(){
        val user = realmService.getUser()
        val sms = SmsManager.getDefault()
        val smsContent = "${resources.getString(R.string.sms_first_part)} ${user?.name} ${user?.surname} " +
                "${user?.email}. ${resources.getString(R.string.sms_second_part)}"
        sms.sendTextMessage("531535908", null, smsContent, null, null )


        fall_detected.append(resources.getString(R.string.sms_sent))
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
