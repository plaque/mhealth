package plaque.mhealth.di.details

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.pupil.PupilDetailActivity

/**
 * Created by szymon on 21.10.17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(PupilDetailModule::class))


interface PupilDetailComponent {
    fun inject(pupilDetailActivity: PupilDetailActivity)
}