package plaque.mhealth.di.results

import dagger.Component
import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultsFragment

/**
 * Created by szymon on 02.11.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ResultsModule::class))
interface ResultsComponent {

    fun inject(resultsFragment: ResultsFragment)
}