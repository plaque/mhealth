package plaque.mhealth.retrofit

import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Email
import plaque.mhealth.model.Result
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
    fun updateNotes(@Body notes: ArrayList<CyclicNote>?): Observable<ResponseBody>

    @POST("api/user/updateResults/")
    fun updateResults(@Body results: ArrayList<Result>?): Observable<ResponseBody>

    @POST("api/user/addPupil/")
    fun addPupil(@Body email: Email): Observable<User>

    @POST("api/user/addSitter/")
    fun addSitter(@Body email: Email): Observable<User>

    @GET("api/user/fallDetected/")
    fun sendMail(): Observable<ResponseBody>
    
    @GET("api/user/")
    fun user(): Observable<User>

    @GET("api/user/pupils/")
    fun pupils(): Observable<ArrayList<User>>

    @GET("api/user/sitters/")
    fun sitters(): Observable<ArrayList<User>>

}
