package plaque.mhealth.DI.Meds

import dagger.Subcomponent
import plaque.mhealth.Fragments.MedsFragment
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Singleton
@Subcomponent(modules = arrayOf(MedsModule::class))

interface MedsComponent {

    fun inject(medsFragment: MedsFragment)
}