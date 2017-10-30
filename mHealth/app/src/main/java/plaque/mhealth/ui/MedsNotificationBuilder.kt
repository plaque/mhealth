package plaque.mhealth.ui

import android.content.Context
import android.support.v4.app.NotificationCompat
import plaque.mhealth.R
import plaque.mhealth.model.CyclicNote

/**
 * Created by szymon on 30.10.17.
 */
class MedsNotificationBuilder(note: CyclicNote?,
                              context: Context, username: String? ): NotificationCompat.Builder(context) {

    val CHANNEL_ID : String = "meds_channel"

    init{
        super.setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.drugs)
                .setContentTitle("Meds alert for user $username")
                .setContentText("Alert for note ${note?.title}: ${note?.content}")
    }

}