package plaque.mhealth.DI.Meds

import dagger.Module
import dagger.Provides
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Database.RealmService
import plaque.mhealth.UI.UserSlider.Fragments.Meds.MedsFragment
import plaque.mhealth.UI.UserSlider.Fragments.Meds.MedsPresenter
import plaque.mhealth.UI.UserSlider.Fragments.Meds.MedsPresenterImpl

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