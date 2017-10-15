package plaque.mhealth.di.pupils

import dagger.Module
import dagger.Provides
import plaque.mhealth.database.DataStore
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_slider.fragments.pupils.PupilsFragment
import plaque.mhealth.ui.user_slider.fragments.pupils.PupilsPresenter
import plaque.mhealth.ui.user_slider.fragments.pupils.PupilsPresenterImpl

/**
 * Created by szymon on 15.10.17.
 */
@Module
class PupilsModule{

    @Provides
    @ActivityScope
    fun providesPupilsFragment() = PupilsFragment

    @Provides
    fun providePupilsPresenter(dataStore: DataStore): PupilsPresenter = PupilsPresenterImpl(dataStore)
}