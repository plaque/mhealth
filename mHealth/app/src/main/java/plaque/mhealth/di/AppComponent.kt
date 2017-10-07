package plaque.mhealth.di

import dagger.Component
import plaque.mhealth.di.login.LoginComponent
import plaque.mhealth.di.login.LoginModule
import plaque.mhealth.di.meds.MedsComponent
import plaque.mhealth.di.meds.MedsModule
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

}