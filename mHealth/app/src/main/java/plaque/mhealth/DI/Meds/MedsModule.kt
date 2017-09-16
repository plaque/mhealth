package plaque.mhealth.DI.Meds

import dagger.Module
import dagger.Provides
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Android.Fragments.MedsFragment
import plaque.mhealth.Retrofit.LoginAPI
import plaque.mhealth.Retrofit.LoginRestAPI
import plaque.mhealth.Retrofit.UserAPI
import plaque.mhealth.Retrofit.UserRestAPI
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Module
class MedsModule {

    @Provides
    @ActivityScope
    fun provideMedsFragment() = MedsFragment



}