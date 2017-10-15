package plaque.mhealth.retrofit

import io.reactivex.Observable
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 16.09.17.
 */
@ActivityScope
class UserRestAPI @Inject constructor(private var userAPI: UserAPI) {

    fun saveUser(user: User) = userAPI.saveUser(user)

    fun getUser(): Observable<User> = userAPI.user()

    fun getPupils(): Observable<ArrayList<User>> = userAPI.pupils()

    fun getSitters(): Observable<ArrayList<User>> = userAPI.sitters()
}