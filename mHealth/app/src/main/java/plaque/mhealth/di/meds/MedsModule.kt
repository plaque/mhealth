package plaque.mhealth.di.meds

import dagger.Module
import dagger.Provides
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.database.RealmService
import plaque.mhealth.ui.user_slider.fragments.meds.MedsFragment
import plaque.mhealth.ui.user_slider.fragments.meds.MedsPresenter
import plaque.mhealth.ui.user_slider.fragments.meds.MedsPresenterImpl

/**
 * Created by szymon on 15.09.17.
 */
@Module
class MedsModule {

    @Provides
    @ActivityScope
    fun provideMedsFragment() = MedsFragment

    @Provides
    fun provideMedsPresenter(realmService: RealmService): MedsPresenter = MedsPresenterImpl(realmService)

}