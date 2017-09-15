package plaque.mhealth.DI

import dagger.Module
import dagger.Provides
import plaque.mhealth.Retrofit.LoginAPI
import plaque.mhealth.Retrofit.LoginRestAPI
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Module
class NoteModule {

    @Provides
    @Singleton
    fun provideLoginRestAPI(loginAPI: LoginAPI): LoginRestAPI = LoginRestAPI(loginAPI)

    @Provides
    @Singleton
    fun provideLoginAPI(retrofit: Retrofit): LoginAPI = retrofit.create(LoginAPI::class.java)

}