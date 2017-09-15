package plaque.mhealth.DI.Meds

import dagger.Component
import dagger.Subcomponent
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.NetworkModule
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Fragments.MedsFragment
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MedsModule::class))

interface MedsComponent {

    fun inject(medsFragment: MedsFragment)
}