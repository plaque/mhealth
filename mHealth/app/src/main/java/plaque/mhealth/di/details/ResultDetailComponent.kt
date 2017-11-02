package plaque.mhealth.di.details

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultDetailActivity

/**
 * Created by szymon on 02.11.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ResultDetailModule::class))

interface ResultDetailComponent {
    fun inject(resultDetailActivity: ResultDetailActivity)
}