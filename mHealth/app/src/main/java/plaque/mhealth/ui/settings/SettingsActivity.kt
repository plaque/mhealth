package plaque.mhealth.ui.settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import plaque.mhealth.R
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.model.Settings
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {

    @Inject lateinit var realmService: RealmService
    var settings: Settings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mHealthApp.settingsComponent.inject(this)

        this.settings = realmService.getSettings()

        updateView()

    }

    private fun updateView(){

        fall_monitoring.isChecked = this.settings?.fallMonitoring ?: true
        sms_notifs.isChecked = this.settings?.sms ?: true
        email_notifs.isChecked = this.settings?.emails ?: true
        push_notifs.isChecked = this.settings?.push ?: true

    }

    private fun saveSettings(){

        val settings = Settings(push_notifs.isChecked, email_notifs.isChecked,
                sms_notifs.isChecked, fall_monitoring.isChecked)

        realmService.saveSettings(settings)
    }

    override fun onDestroy() {
        super.onDestroy()
        saveSettings()
    }
}
