package plaque.mhealth.Retrofit

import io.reactivex.Observable
import okhttp3.ResponseBody
import plaque.mhealth.Model.Login
import plaque.mhealth.Model.User
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by szymon on 15.09.17.
 */
@Singleton
class LoginRestAPI @Inject constructor(private val loginAPI: LoginAPI){

    fun user(): Observable<User> = loginAPI.user()

    fun login(login:Login): Observable<Response<ResponseBody>> = loginAPI.login(login)
}