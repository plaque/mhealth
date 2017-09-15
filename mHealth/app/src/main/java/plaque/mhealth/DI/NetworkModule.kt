package plaque.mhealth.DI

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import plaque.mhealth.Retrofit.TokenInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): TokenInterceptor = TokenInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: TokenInterceptor): OkHttpClient
            = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, converterFactory: GsonConverterFactory, callAdapterFactory: RxJava2CallAdapterFactory): Retrofit
            = Retrofit.Builder()
            .baseUrl("http://192.168.0.10:8081")
            .addConverterFactory(converterFactory)
            .client(httpClient)
            .addCallAdapterFactory(callAdapterFactory).build()

}