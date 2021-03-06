package plaque.mhealth.database

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Email
import plaque.mhealth.model.Result
import plaque.mhealth.model.User
import plaque.mhealth.retrofit.UserRestAPI
import javax.inject.Inject

/**
 * Created by szymon on 15.10.17.
 */
class DataStore @Inject constructor(val realm: RealmService, val api: UserRestAPI) {

    fun getUser(): Observable<User>{

        val userObservable: Observable<User>
        val user = realm.getUser()
        if(user == null){
            userObservable = api.getUser()
        }else{
            userObservable = Observable.create<User> {
                subscriber -> subscriber.onNext(user)
            }
        }

        return userObservable
    }

    fun saveUser(user: User){

        if(realm.getUser() == null){
            realm.saveUser(user)
        }
        else{
            realm.saveUser(user)
            api.saveUser(user).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        response -> print(response)
                    },{
                        response -> print(response)
                    })
        }
    }

    fun updateUser(user: User){
        realm.updateUser(user)
    }

    fun updateNotes(user: User){
        realm.updateUser(user)
        api.updateNotes(user.notes).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response -> println(response)
                },{
                    e -> println(e.message.toString())
                })
    }

    fun getNotes(): Observable<ArrayList<CyclicNote>> = realm.getNotes()

    fun updateResults(user: User){
        realm.updateUser(user)
        api.updateResults(user.results).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response -> println(response)
                },{
                    e -> println(e.message.toString())
                })

    }

    fun getResults(): Observable<ArrayList<Result>> = realm.getResults()

    fun getPupils(): Observable<ArrayList<User>>{

        val pupilsObservable: Observable<ArrayList<User>>
        val pupils = realm.getPupils()

        if(pupils.size == 0){
            pupilsObservable = api.getPupils()
        }else{
            pupilsObservable = Observable.create {
                subscriber -> subscriber.onNext(pupils)
            }
        }

        return pupilsObservable
    }

    fun getSitters(): Observable<ArrayList<User>>{

        val sittersObservable: Observable<ArrayList<User>>
        val sitters = realm.getSitters()
        if(sitters.size == 0){
            sittersObservable = api.getSitters()
        }else{
            sittersObservable = Observable.create {
                subscriber -> subscriber.onNext(sitters)
            }
        }

        return sittersObservable
    }


    fun closeRealm() = realm.closeRealm()

    fun saveSitters(sitters: ArrayList<User>) {
        val user = realm.getUser()
        user?.sitters = sitters
        realm.updateUser(user!!)
    }

    fun savePupils(pupils: ArrayList<User>) {
        val user = realm.getUser()
        user?.pupils = pupils
        realm.updateUser(user!!)
    }

    fun addPupil(email: String): Observable<User> = api.addPupil(email)

    fun addSitter(email: String): Observable<User> = api.addSitter(email)

    fun getSettings() = realm.getSettings()

    fun saveSitters(email: String, sitters: ArrayList<User>){
        val user = realm.getUser()
        user?.pupils?.find { it.email == email }?.sitters = sitters
        realm.updateUser(user!!)
    }

    fun getSitters(email: String): Observable<ArrayList<User>> {

        val sittersObservable: Observable<ArrayList<User>>
        val sitters = realm.getSitters(email)
        if(sitters.size == 0){
            sittersObservable = api.getSitters(Email(email))
        }else{
            sittersObservable = Observable.create {
                subscriber -> subscriber.onNext(sitters)
            }
        }

        return sittersObservable
    }


}