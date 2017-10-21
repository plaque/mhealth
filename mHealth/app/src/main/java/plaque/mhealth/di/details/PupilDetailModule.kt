package plaque.mhealth.di.details

import dagger.Module
import dagger.Provides
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.pupil.PupilDetailActivity

/**
 * Created by szymon on 21.10.17.
 */
@Module
class PupilDetailModule {

    @Provides
    @ActivityScope
    fun providePupilDetailActivity() = PupilDetailActivity()

}