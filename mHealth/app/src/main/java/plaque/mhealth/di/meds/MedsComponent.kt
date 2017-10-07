package plaque.mhealth.di.meds

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_slider.fragments.meds.MedsFragment


/**
 * Created by szymon on 15.09.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MedsModule::class))

interface MedsComponent {

    fun inject(medsFragment: MedsFragment)
}