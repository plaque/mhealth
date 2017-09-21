package plaque.mhealth.DI.Login

import dagger.Module
import dagger.Provides
import plaque.mhealth.UI.Login.LoginActivity
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Database.RealmService
import plaque.mhealth.Retrofit.LoginRestAPI
import plaque.mhealth.UI.Login.LoginPresenter
import plaque.mhealth.UI.Login.LoginPresenterImpl

/**
 * Created by szymon on 15.09.17.
 */
@Module
class LoginModule{

    @Provides
    @ActivityScope
    fun provideLoginActivity() = LoginActivity()

    @Provides
    fun provideLoginPresenter(api: LoginRestAPI, realmService: RealmService): LoginPresenter
            = LoginPresenterImpl(api, realmService)

}