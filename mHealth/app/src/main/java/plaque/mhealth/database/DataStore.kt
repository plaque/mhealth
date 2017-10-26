package plaque.mhealth.database

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import plaque.mhealth.model.CyclicNote
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
        api.updateNotes(user.notes)
    }

    fun getNotes(): Observable<ArrayList<CyclicNote>> = realm.getNotes()

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
            sittersObservable = api.getPupils()
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


}