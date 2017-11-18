package plaque.mhealth.di.settings

import dagger.Module
import plaque.mhealth.ui.settings.SettingsActivity

/**
 * Created by szymon on 18.11.17.
 */
@Module
class SettingsModule {
    fun provideSettingsActivity() = SettingsActivity()
}