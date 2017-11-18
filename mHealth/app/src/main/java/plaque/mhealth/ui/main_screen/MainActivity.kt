package plaque.mhealth.ui.main_screen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main1.*
import plaque.mhealth.R
import plaque.mhealth.database.DataStore
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Settings
import plaque.mhealth.ui.settings.SettingsActivity
import plaque.mhealth.ui.user_main_slider.TasksActivity
import plaque.mhealth.ui.user_main_slider.fragments.meds.MedsAlertStarter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var dataStore: DataStore
    var settings: Settings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        mHealthApp.mainComponent.inject(this)

        settings = dataStore.getSettings()

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
                    if(settings?.push ?: false) alertStarter.startAlarms(user)
                },{
                    e -> println(e.message)
                })
    }
}
