package plaque.mhealth.Retrofit

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by szymon on 15.09.17.
 */
@Singleton
class TokenInterceptor @Inject constructor() : Interceptor {
    private var sessionToken: String? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        var response : Response

        val requestBuilder = request.newBuilder()

        if(request.url().toString().contains("login")){
            response = chain.proceed(requestBuilder.build())
            sessionToken = response.header("Authorization")
        }
        else{

            if (sessionToken == null) {
                throw RuntimeException("Session token should be defined for auth apis")
            } else {
                requestBuilder.addHeader("Authorization", sessionToken)
            }

            response = chain.proceed(requestBuilder.build())
        }


        return response

    }
}