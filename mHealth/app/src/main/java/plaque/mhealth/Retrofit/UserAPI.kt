package plaque.mhealth.Retrofit

import io.reactivex.Observable
import plaque.mhealth.Model.User
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by szymon on 12.09.17.
 */

interface UserAPI {

    @GET("api/user/")
    fun user(): Observable<User>

    @GET("api/user/patients/")
    fun patients(): Observable<List<User>>

    @GET("api/user/patrons/")
    fun patrons(): Observable<List<User>>

}