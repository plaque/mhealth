package plaque.mhealth.Retrofit

import android.text.TextUtils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by szymon on 11.09.17.
 */
//
//class ServiceGenerator {
//
//    val API_BASE_URL = "http://192.168.0.10:8081"
//
//    private val httpClient = OkHttpClient.Builder()
//
//    private val builder = Retrofit.Builder()
//            .baseUrl(API_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//
//    private var retrofit: Retrofit = builder.build()
//
//    fun <S> createService(serviceClass: Class<S>): S {
//        return createService(serviceClass, null)
//    }
//
//    fun <S> createService(
//            serviceClass: Class<S>, authToken: String?): S {
//        if (!TextUtils.isEmpty(authToken)) {
//            val interceptor = AuthenticationInterceptor(authToken!!)
//
//            if (!httpClient.interceptors().contains(interceptor)) {
//                httpClient.addInterceptor(interceptor)
//
//                builder.client(httpClient.build())
//                retrofit = builder.build()
//            }
//        }
//
//        return retrofit.create(serviceClass)
//    }
//}
