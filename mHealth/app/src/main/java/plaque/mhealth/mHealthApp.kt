package plaque.mhealth

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import io.realm.Realm
import io.realm.RealmConfiguration
import plaque.mhealth.di.AppComponent
import plaque.mhealth.di.AppModule
import plaque.mhealth.di.DaggerAppComponent
import plaque.mhealth.di.login.LoginComponent
import plaque.mhealth.di.login.LoginModule
import plaque.mhealth.di.meds.MedsComponent
import plaque.mhealth.di.meds.MedsModule

/**
 * Created by szymon on 15.09.17.
 */
class mHealthApp: Application(){

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var medsComponent: MedsComponent
        lateinit var loginComponent: LoginComponent
    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        medsComponent = appComponent.plus(MedsModule())
        loginComponent = appComponent.plus(LoginModule())

        initRealmConfiguration()
    }

    fun initRealmConfiguration(){
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
    }

}