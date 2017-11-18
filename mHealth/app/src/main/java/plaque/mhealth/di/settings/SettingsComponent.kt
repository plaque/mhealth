package plaque.mhealth.di.settings

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.settings.SettingsActivity

/**
 * Created by szymon on 18.11.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(SettingsModule::class))

interface SettingsComponent {
    fun inject(settingsActivity: SettingsActivity)
}