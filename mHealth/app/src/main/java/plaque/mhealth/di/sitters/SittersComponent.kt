package plaque.mhealth.di.sitters

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.user_main_slider.fragments.sitters.SittersFragment

/**
 * Created by szymon on 15.10.17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(SittersModule::class))

interface SittersComponent {
    fun inject(sittersFragment: SittersFragment)
}