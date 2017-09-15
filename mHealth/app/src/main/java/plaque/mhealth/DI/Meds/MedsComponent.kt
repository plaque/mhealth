package plaque.mhealth.DI.Meds

import dagger.Component
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.NetworkModule
import plaque.mhealth.Fragments.MedsFragment
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class)
)

interface MedsComponent {

    fun inject(medsFragment: MedsFragment)
}