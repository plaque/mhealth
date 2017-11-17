package plaque.mhealth.di.fall

import dagger.Module
import dagger.Provides
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.activities.FallDetectedActivity

/**
 * Created by szymon on 17.11.17.
 */
@Module
class FallModule {

    @Provides
    @ActivityScope
    fun provideFallDetectedActivity() = FallDetectedActivity()
}