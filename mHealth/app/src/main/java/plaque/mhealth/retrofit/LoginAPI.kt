package plaque.mhealth.retrofit

import io.reactivex.Observable
import okhttp3.ResponseBody
import plaque.mhealth.model.Login
import retrofit2.http.Body
import retrofit2.http.POST
/**
 * Created by szymon on 11.09.17.
 */
interface LoginAPI {

    @POST("login")
    fun login(@Body login: Login) : Observable<retrofit2.Response<ResponseBody>>

}