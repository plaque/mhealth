package plaque.mhealth.DI.Login

import dagger.Component
import dagger.Subcomponent
import plaque.mhealth.Activities.LoginActivity
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.NetworkModule
import plaque.mhealth.DI.Scopes.ActivityScope
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))

interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}