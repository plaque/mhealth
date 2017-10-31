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
                              context: Context): NotificationCompat.Builder(context) {

    val CHANNEL_ID : String = "meds_channel"

    init{
        super.setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.drugs)
                .setContentTitle("Meds alert for user $email")
                .setContentText("Alert for note $title: $content")
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setColor(Color.GREEN)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
    }

}