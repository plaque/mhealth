package plaque.mhealth.di.main

import dagger.Subcomponent
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.ui.main_screen.MainActivity

/**
 * Created by szymon on 23.10.17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(MainModule::class))

interface MainComponent{

    fun inject(mainActivity: MainActivity)

}
