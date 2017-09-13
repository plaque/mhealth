package plaque.mhealth.Retrofit

import plaque.mhealth.Model.Login
import plaque.mhealth.Model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by szymon on 11.09.17.
 */
interface LoginService {

    @POST("/login")
    fun login(@Body login: Login) : Call<Unit>

}