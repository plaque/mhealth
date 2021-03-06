package plaque.mhealth.di

import dagger.Component
import plaque.mhealth.di.details.PupilDetailComponent
import plaque.mhealth.di.details.PupilDetailModule
import plaque.mhealth.di.details.ResultDetailComponent
import plaque.mhealth.di.details.ResultDetailModule
import plaque.mhealth.di.fall.FallComponent
import plaque.mhealth.di.fall.FallModule
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
import plaque.mhealth.di.settings.SettingsComponent
import plaque.mhealth.di.settings.SettingsModule
import plaque.mhealth.di.sitters.SittersComponent
import plaque.mhealth.di.sitters.SittersModule
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class
))
interface AppComponent {

    fun plus(medsModule: MedsModule): MedsComponent

    fun plus(loginModule: LoginModule): LoginComponent

    fun plus(pupilsModule: PupilsModule): PupilsComponent

    fun plus(sittersModule: SittersModule): SittersComponent

    fun plus(pupilDetailModule: PupilDetailModule): PupilDetailComponent

    fun plus(resultDetailModule: ResultDetailModule): ResultDetailComponent

    fun plus(mainModule: MainModule): MainComponent

    fun plus(resultsModule: ResultsModule): ResultsComponent

    fun plus(fallModule: FallModule): FallComponent

    fun plus(settingsModule: SettingsModule): SettingsComponent

}