package plaque.mhealth.di.pupils

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.pupils.PupilsFragment

/**
 * Created by szymon on 15.10.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(PupilsModule::class))

interface PupilsComponent{
    fun inject(pupilsFragment: PupilsFragment)
}