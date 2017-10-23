package plaque.mhealth.di.main

import dagger.Module
import dagger.Provides
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.main_screen.MainActivity

/**
 * Created by szymon on 23.10.17.
 */
@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainActivity() = MainActivity()
}