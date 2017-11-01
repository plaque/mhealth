package plaque.mhealth.retrofit

import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody
import plaque.mhealth.di.scopes.ActivityScope
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Email
import plaque.mhealth.model.Result
import plaque.mhealth.model.User
import javax.inject.Inject

/**
 * Created by szymon on 16.09.17.
 */
@ActivityScope
class UserRestAPI @Inject constructor(private var userAPI: UserAPI) {

    fun saveUser(user: User) = userAPI.saveUser(user)

    fun getUser(): Observable<User> = userAPI.user()

    fun addPupil(email: String): Observable<User> = userAPI.addPupil(Email(email))

    fun addSitter(email: String): Observable<User> = userAPI.addSitter(Email(email))

    fun updateNotes(notes: ArrayList<CyclicNote>?): Observable<ResponseBody> = userAPI.updateNotes(notes)

    fun updateResults(results: ArrayList<Result>?): Observable<ResponseBody> = userAPI.updateResults(results)

    fun getPupils(): Observable<ArrayList<User>> = userAPI.pupils()

    fun getSitters(): Observable<ArrayList<User>> = userAPI.sitters()
}