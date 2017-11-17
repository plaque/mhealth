package plaque.mhealth.di.fall

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.activities.FallDetectedActivity

/**
 * Created by szymon on 17.11.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(FallModule::class))

interface FallComponent {
    fun inject(fallDetectedActivity: FallDetectedActivity)
}