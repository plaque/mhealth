package plaque.mhealth

import android.app.Application
import plaque.mhealth.DI.AppModule
import plaque.mhealth.DI.Login.DaggerLoginComponent
import plaque.mhealth.DI.Login.LoginComponent
import plaque.mhealth.DI.Meds.DaggerMedsComponent
import plaque.mhealth.DI.Meds.MedsComponent

/**
 * Created by szymon on 15.09.17.
 */
class mHealthApp: Application(){

    companion object {
        lateinit var medsComponent: MedsComponent
        lateinit var loginComponent: LoginComponent
    }

    override fun onCreate() {
        super.onCreate()
        medsComponent = DaggerMedsComponent.builder()
                .appModule(AppModule(this))
                .build()
        loginComponent = DaggerLoginComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}