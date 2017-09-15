package plaque.mhealth

import android.app.Application
import plaque.mhealth.DI.AppComponent
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.DaggerAppComponent
import plaque.mhealth.DI.Login.LoginComponent
import plaque.mhealth.DI.Login.LoginModule
import plaque.mhealth.DI.Meds.MedsComponent
import plaque.mhealth.DI.Meds.MedsModule

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
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        medsComponent = appComponent.plus(MedsModule())
        loginComponent = appComponent.plus(LoginModule())
    }

}