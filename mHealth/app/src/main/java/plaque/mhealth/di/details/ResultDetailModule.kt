package plaque.mhealth.di.details

import dagger.Module
import dagger.Provides
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.results.ResultDetailActivity

/**
 * Created by szymon on 02.11.17.
 */
@Module
class ResultDetailModule {

    @Provides
    @ActivityScope
    fun provideResultDetailActivity() = ResultDetailActivity()

}