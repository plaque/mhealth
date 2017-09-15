package plaque.mhealth.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import plaque.mhealth.mHealthApp
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Module
class AppModule(val app: mHealthApp){

    @Provides
    @Singleton
    fun provideContext():Context = app

    @Provides
    @Singleton
    fun provideApplication(): mHealthApp = app

}