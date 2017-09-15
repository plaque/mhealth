package plaque.mhealth.Retrofit

import io.reactivex.Observable
import plaque.mhealth.Model.User
import retrofit2.http.GET

/**
 * Created by szymon on 12.09.17.
 */
interface UserAPI {

    @GET("/api/user")
    fun user() : Observable<User>
}