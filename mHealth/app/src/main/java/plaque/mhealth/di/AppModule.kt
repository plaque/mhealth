package plaque.mhealth.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import plaque.mhealth.database.DataStore
import plaque.mhealth.database.RealmService
import plaque.mhealth.mHealthApp
import plaque.mhealth.retrofit.UserRestAPI
import javax.inject.Named
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
    @Named("default")
    fun provideRealm() = Realm.getDefaultInstance()

    @Provides
    @Named("token")
    fun provideTokenRealm() = Realm.getInstance(RealmConfiguration.Builder().name("tokenRealm").build())

    @Provides
    fun provideRealmService(@Named("default") realm: Realm, @Named("token") tokenRealm: Realm)
            = RealmService(realm, tokenRealm)

    @Provides
    fun provideDataStore(realm: RealmService, api: UserRestAPI) = DataStore(realm, api)
}