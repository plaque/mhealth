package plaque.mhealth.DI.Meds

import dagger.Subcomponent
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Android.Fragments.MedsFragment


/**
 * Created by szymon on 15.09.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MedsModule::class))

interface MedsComponent {

    fun inject(medsFragment: MedsFragment)
}