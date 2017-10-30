package plaque.mhealth.ui.user_main_slider.fragments.meds

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User
import java.util.*

/**
 * Created by szymon on 30.10.17.
 */
class MedsAlertStarter(val context: Context?) {

    val alarmManager: AlarmManager

    init {
        alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun startAlarms(user: User){

        user.notes?.forEach {
            if(it.active){
                startAlarm(user.email, it)
            }
        }

        user.pupils?.forEach {
            var pupil = it
            it.notes?.forEach {
                if(it.active){
                    startAlarm(pupil.email, it)
                }
            }
        }
    }

    private fun startAlarm(email: String, note: CyclicNote){

        val intent = Intent(context, MedsAlertReceiver::class.java)
        intent.putExtra("title", note.title)
                .putExtra("content", note.content)
                .putExtra("email", email)

        note.days.forEach {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.set(Calendar.DAY_OF_WEEK, it)
            note.hours.forEach{
                var pendingIntent = PendingIntent.getBroadcast(context, Random().nextInt(10000), intent,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                cal.set(Calendar.HOUR_OF_DAY, it.hour)
                cal.set(Calendar.MINUTE, it.minute)
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.timeInMillis,
                        24 * 7 * 60 * 60 * 1000, pendingIntent)
            }
        }

    }
}