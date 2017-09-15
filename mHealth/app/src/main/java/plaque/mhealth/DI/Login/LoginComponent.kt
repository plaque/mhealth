package plaque.mhealth.DI.Login

import dagger.Subcomponent
import plaque.mhealth.Activities.LoginActivity
import plaque.mhealth.DI.Scopes.UserScope
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */

@Singleton
@Subcomponent(modules = arrayOf(LoginModule::class))

interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}