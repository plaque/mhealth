package plaque.mhealth.DI.Login

import dagger.Component
import plaque.mhealth.Activities.LoginActivity
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.NetworkModule
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        LoginModule::class,
        NetworkModule::class)
)

interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}