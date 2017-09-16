package plaque.mhealth.DI

import dagger.Component
import plaque.mhealth.DI.Login.LoginComponent
import plaque.mhealth.DI.Login.LoginModule
import plaque.mhealth.DI.Meds.MedsComponent
import plaque.mhealth.DI.Meds.MedsModule
import plaque.mhealth.Retrofit.UserAPI
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