package plaque.mhealth.ui.user_main_slider.fragments.meds

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by szymon on 30.10.17.
 */
class MedsAlertReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {

        val notificationManager: NotificationManager
                = p0?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = p1?.getIntExtra("id", 1) ?: 1
        notificationManager.notify(id,
        MedsNotificationBuilder(p1?.getStringExtra("title"), p1?.getStringExtra("content"),
                p1?.getStringExtra("email"), p0).build()
        )
    }
}