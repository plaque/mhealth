package plaque.mhealth.di.login

import dagger.Module
import dagger.Provides
import plaque.mhealth.ui.login.LoginActivity
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.database.RealmService
import plaque.mhealth.retrofit.LoginRestAPI
import plaque.mhealth.retrofit.TokenInterceptor
import plaque.mhealth.ui.login.LoginPresenter
import plaque.mhealth.ui.login.LoginPresenterImpl

/**
 * Created by szymon on 15.09.17.
 */
@Module
class LoginModule{

    @Provides
    @ActivityScope
    fun provideLoginActivity() = LoginActivity()

    @Provides
    fun provideLoginPresenter(api: LoginRestAPI, realmService: RealmService,
                              loginInterceptor: TokenInterceptor): LoginPresenter
            = LoginPresenterImpl(api, realmService, loginInterceptor)

}