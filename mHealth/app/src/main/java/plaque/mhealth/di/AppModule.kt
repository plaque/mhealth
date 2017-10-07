package plaque.mhealth.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import plaque.mhealth.database.RealmService
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

    @Provides
    fun provideRealm() = Realm.getDefaultInstance()

    @Provides
    fun provideRealmService(realm: Realm) = RealmService(realm)
}