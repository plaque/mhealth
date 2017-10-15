package plaque.mhealth.di.sitters

import dagger.Module
import dagger.Provides
import plaque.mhealth.database.DataStore
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersFragment
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersPresenter
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersPresenterImpl

/**
 * Created by szymon on 15.10.17.
 */
@Module
class SittersModule {

    @Provides
    @ActivityScope
    fun provideSittersFragment() = SittersFragment

    @Provides
    fun provideSittersPresenter(dataStore: DataStore): SittersPresenter = SittersPresenterImpl(dataStore)
}