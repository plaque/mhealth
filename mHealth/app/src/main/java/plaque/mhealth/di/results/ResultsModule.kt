package plaque.mhealth.di.results

import dagger.Module
import dagger.Provides
import plaque.mhealth.database.DataStore
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsFragment
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsPresenter
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsPresenterImpl

/**
 * Created by szymon on 02.11.17.
 */
@Module
class ResultsModule {

    @Provides
    @ActivityScope
    fun provideResultsFragment() = ResultsFragment

    @Provides
    fun provideResultsPresenter(dataStore: DataStore): ResultsPresenter = ResultsPresenterImpl(dataStore)



}