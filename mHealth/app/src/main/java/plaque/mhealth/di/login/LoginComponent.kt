package plaque.mhealth.di.login

import dagger.Subcomponent
import plaque.mhealth.ui.login.LoginActivity
import plaque.mhealth.di.scopes.ActivityScope

/**
 * Created by szymon on 15.09.17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))

interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}