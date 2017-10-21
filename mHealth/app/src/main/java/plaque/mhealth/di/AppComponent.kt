package plaque.mhealth.di

import dagger.Component
import plaque.mhealth.di.details.PupilDetailComponent
import plaque.mhealth.di.details.PupilDetailModule
import plaque.mhealth.di.login.LoginComponent
import plaque.mhealth.di.login.LoginModule
import plaque.mhealth.di.meds.MedsComponent
import plaque.mhealth.di.meds.MedsModule
import plaque.mhealth.di.pupils.PupilsComponent
import plaque.mhealth.di.pupils.PupilsModule
import plaque.mhealth.di.sitters.SittersComponent
import plaque.mhealth.di.sitters.SittersModule
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class
))
interface AppComponent {

    fun plus(medsModule: MedsModule): MedsComponent

    fun plus(loginModule: LoginModule): LoginComponent

    fun plus(pupilsModule: PupilsModule): PupilsComponent

    fun plus(sittersModule: SittersModule): SittersComponent

    fun plus(pupilDetailModule: PupilDetailModule): PupilDetailComponent

}