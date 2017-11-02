package plaque.mhealth

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import io.realm.Realm
import io.realm.RealmConfiguration
import plaque.mhealth.di.AppComponent
import plaque.mhealth.di.AppModule
import plaque.mhealth.di.DaggerAppComponent
import plaque.mhealth.di.details.PupilDetailComponent
import plaque.mhealth.di.details.PupilDetailModule
import plaque.mhealth.di.details.ResultDetailComponent
import plaque.mhealth.di.details.ResultDetailModule
import plaque.mhealth.di.login.LoginComponent
import plaque.mhealth.di.login.LoginModule
import plaque.mhealth.di.main.MainComponent
import plaque.mhealth.di.main.MainModule
import plaque.mhealth.di.meds.MedsComponent
import plaque.mhealth.di.meds.MedsModule
import plaque.mhealth.di.pupils.PupilsComponent
import plaque.mhealth.di.pupils.PupilsModule
import plaque.mhealth.di.results.ResultsComponent
import plaque.mhealth.di.results.ResultsModule
import plaque.mhealth.di.sitters.SittersComponent
import plaque.mhealth.di.sitters.SittersModule

/**
 * Created by szymon on 15.09.17.
 */
class mHealthApp: Application(){

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var mainComponent: MainComponent
        lateinit var medsComponent: MedsComponent
        lateinit var loginComponent: LoginComponent
        lateinit var pupilsComponent: PupilsComponent
        lateinit var sittersComponent: SittersComponent
        lateinit var pupilDetailComponent: PupilDetailComponent
        lateinit var resultsComponent: ResultsComponent
        lateinit var resultDetailComponent: ResultDetailComponent
    }

    override fun onCreate() {
        super.onCreate()

        initRealmConfiguration()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        pupilDetailComponent = appComponent.plus(PupilDetailModule())
        mainComponent = appComponent.plus(MainModule())
        medsComponent = appComponent.plus(MedsModule())
        loginComponent = appComponent.plus(LoginModule())
        pupilsComponent = appComponent.plus(PupilsModule())
        sittersComponent = appComponent.plus(SittersModule())
        resultsComponent = appComponent.plus(ResultsModule())
        resultDetailComponent = appComponent.plus(ResultDetailModule())

    }

    private fun initRealmConfiguration(){
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
    }

}