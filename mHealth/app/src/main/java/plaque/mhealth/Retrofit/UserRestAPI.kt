package plaque.mhealth.Retrofit

import io.reactivex.Observable
import plaque.mhealth.DI.Scopes.ActivityScope
import plaque.mhealth.Model.User
import javax.inject.Inject

/**
 * Created by szymon on 16.09.17.
 */
@ActivityScope
class UserRestAPI @Inject constructor(private var userAPI: UserAPI) {

    fun getUser(): Observable<User> = userAPI.user()

    fun getPatients(): Observable<List<User>> = userAPI.patients()

    fun getPatrons(): Observable<List<User>> = userAPI.patrons()
}