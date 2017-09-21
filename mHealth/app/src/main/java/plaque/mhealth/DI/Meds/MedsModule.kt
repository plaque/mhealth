package plaque.mhealth.DI.Meds

import dagger.Module
import dagger.Provides
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.UI.Android.Fragments.MedsFragment

/**
 * Created by szymon on 15.09.17.
 */
@Module
class MedsModule {

    @Provides
    @ActivityScope
    fun provideMedsFragment() = MedsFragment



}