package plaque.mhealth.retrofit

import io.reactivex.Observable
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Email
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

    @POST("api/user/updateNotes/")
    fun updateNotes(@Body notes: ArrayList<CyclicNote>?)

    @POST("api/user/addPupil/")
    fun addPupil(@Body email: Email): Observable<User>

    @POST("api/user/addSitter/")
    fun addSitter(@Body email: Email): Observable<User>

    @GET("api/user/")
    fun user(): Observable<User>

    @GET("api/user/pupils/")
    fun pupils(): Observable<ArrayList<User>>

    @GET("api/user/sitters/")
    fun sitters(): Observable<ArrayList<User>>

}