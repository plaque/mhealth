package plaque.mhealth.DI.Login

import dagger.Module
import dagger.Provides
import plaque.mhealth.Android.Activities.LoginActivity
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Retrofit.LoginAPI
import plaque.mhealth.Retrofit.LoginRestAPI
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Module
class LoginModule{

    @Provides
    @ActivityScope
    fun provideLoginActivity() = LoginActivity()


}