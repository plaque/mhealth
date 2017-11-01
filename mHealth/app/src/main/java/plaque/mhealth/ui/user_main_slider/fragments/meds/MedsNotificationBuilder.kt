package plaque.mhealth.ui.user_main_slider.fragments.meds

import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote

/**
 * Created by szymon on 30.10.17.
 */
class MedsNotificationBuilder(title: String?, content:String?, email: String?,
                              context: Context, CHANNEL_ID : String = "meds_channel"): NotificationCompat.Builder(context, CHANNEL_ID) {



    init{
        super.setSmallIcon(R.drawable.drugs)
                .setContentTitle("Meds alert for user $email")
                .setContentText("Alert for note $title: $content")
                .setVibrate(longArrayOf(300, 300, 300, 300, 300))
                .setLights(Color.GREEN, 300, 300)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
    }

}