package plaque.mhealth.retrofit

import io.reactivex.Observable
import okhttp3.ResponseBody
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.model.Login
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by szymon on 15.09.17.
 */
@ActivityScope
class LoginRestAPI @Inject constructor (private val loginAPI: LoginAPI){

    fun login(login:Login): Observable<Response<ResponseBody>> = loginAPI.login(login)
}