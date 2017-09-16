package plaque.mhealth.DI.Login

import dagger.Subcomponent
import plaque.mhealth.Android.Activities.LoginActivity
import plaque.mhealth.DI.Scopes.ActivityScope

/**
 * Created by szymon on 15.09.17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(LoginModule::class))

interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}