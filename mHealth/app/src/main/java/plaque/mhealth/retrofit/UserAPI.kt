package plaque.mhealth.retrofit

import io.reactivex.Observable
import plaque.mhealth.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by szymon on 12.09.17.
 */

interface UserAPI {

    @POST("api/user/save/")
    fun saveUser(@Body user: User): Observable<User>

    @GET("api/user/")
    fun user(): Observable<User>

    @GET("api/user/pupils/")
    fun pupils(): Observable<ArrayList<User>>

    @GET("api/user/sitters/")
    fun sitters(): Observable<ArrayList<User>>

}