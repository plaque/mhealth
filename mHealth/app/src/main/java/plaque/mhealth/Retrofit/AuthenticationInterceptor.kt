package plaque.mhealth.Retrofit

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by szymon on 11.09.17.
 */

class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
                .header("Authorization", authToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}

