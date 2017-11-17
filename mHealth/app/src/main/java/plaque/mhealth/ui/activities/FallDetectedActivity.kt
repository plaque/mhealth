package plaque.mhealth.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.fall_detected_activity.*
import plaque.mhealth.R

/**
 * Created by szymon on 07.10.17.
 */
class FallDetectedActivity: AppCompatActivity() {

    @Inject lateinit var api: UserRestAPI 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fall_detected_activity)
        
        sendMailWithDelay()

        warning_img.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        dismiss_button.setOnClickListener{ _ -> finish()}
    }
    
    private fun sendMailWithDelay(){
        val timer = Timer()
        timer.schedule(timerTask { sendMail() }, 5 * 1000)
    }
    
    private fun sendMail(){
        api.sendMail().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            _ -> finish()
                        },
                        {
                            e -> print(e.message)
                        }
                )
        
    }
}
