package plaque.mhealth.Retrofit

import io.reactivex.Observable
import plaque.mhealth.Model.User

/**
 * Created by szymon on 16.09.17.
 */
class UserRestAPI(private val userAPI: UserAPI) {

    fun getUser(): Observable<User> = userAPI.user()
}