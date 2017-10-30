package plaque.mhealth.ui.main_screen

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import plaque.mhealth.R
import plaque.mhealth.database.DataStore
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.User
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsNotificationBuilder
import plaque.mhealth.ui.SettingsActivity
import plaque.mhealth.ui.user_main_slider.TasksActivity
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsAlertStarter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHealthApp.mainComponent.inject(this)

        getAndSaveUser()

        setListeners()
    }

    fun startActivityWithExtra(position: Int){
        intent = Intent(this, TasksActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    fun setListeners(){
        settings_button.setOnClickListener { _ -> startActivity(Intent(this, SettingsActivity::class.java)) }

        meds_button.setOnClickListener { _ ->  startActivityWithExtra(0) }

        results_button.setOnClickListener { _ -> startActivityWithExtra(1) }

        sitters_button.setOnClickListener { _ -> startActivityWithExtra(2) }

        pupils_button.setOnClickListener { _ -> startActivityWithExtra(3) }
    }

    fun getAndSaveUser(){
        dataStore.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                  user -> dataStore.saveUser(user)
                    val alertStarter = MedsAlertStarter(this)
                    alertStarter.startAlarms(user)
                    //setAlarms(user)
                },{
                    e -> println(e.message)
                })
    }

  /*  fun notifTest(user: User){
        val notificationManager: NotificationManager
                = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val id = "meds_channel"

        val name: CharSequence = getString(R.string.channel_name)
        val descr: String = getString(R.string.channel_description)

        val importance: Int = NotificationManager.IMPORTANCE_HIGH

        //val channel: NotificationChannel = NotificationChannel()

        notificationManager.notify(1, MedsNotificationBuilder(user.notes!![0].title,
                user.notes!![0].content, user.name, this).build())
    }
 */
 /*   fun setAlarms(user: User){

        user.notes?.forEach {
            if(it.active){
                startAlarm(it)
            }
        }

        user.pupils?.forEach {
            var user = it
            it.notes?.forEach {
                if(it.active){
                    startAlarm(user, it)
                }
            }
        }



    }
*/

}
