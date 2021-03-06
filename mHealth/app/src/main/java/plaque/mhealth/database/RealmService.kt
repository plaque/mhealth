package plaque.mhealth.database

import io.reactivex.Observable
import io.realm.Realm
import plaque.mhealth.database.entities.*
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Result
import plaque.mhealth.model.Settings
import plaque.mhealth.model.User

/**
 * Created by szymon on 21.09.17.
 */
class RealmService(val realm: Realm, val tokenRealm: Realm) {


    fun saveUser(user: User) {

        val user1 = realm.where(UserEntity::class.java).equalTo("email", user.email).findFirst()
        if(user1 == null){
            realm.executeTransaction {
                val userEntity = user.toUserEntity()
                realm.copyToRealm(userEntity)
                System.out.println(userEntity)
            }
        }
    }

    fun getUser(): User? {
        val user1: UserEntity? = realm.where(UserEntity::class.java).findFirst()
        return user1?.toUser()
    }

    fun updateUser(user: User){

        this.deleteAll()
        this.saveUser(user)

    }

    fun deleteAll(){
        realm.executeTransaction {
            realm.deleteAll()
        }
    }

    fun getNotes(): Observable<ArrayList<CyclicNote>>{
        val notesObservable: Observable<ArrayList<CyclicNote>>
        val user1: UserEntity = realm.where(UserEntity::class.java).findFirst()
        val notes: ArrayList<CyclicNote> = user1.toUser().notes!!

        notesObservable = Observable.create {
            subscriber -> subscriber.onNext(notes)
        }

        return notesObservable
    }

    fun getResults(): Observable<ArrayList<Result>>{
        val resultsObservable: Observable<ArrayList<Result>>
        val user1: UserEntity = realm.where(UserEntity::class.java).findFirst()
        val results: ArrayList<Result> = user1.toUser().results!!

        resultsObservable = Observable.create {
            subscriber -> subscriber.onNext(results)
        }

        return resultsObservable
    }

    fun getPupils(): ArrayList<User>{

        val user1: UserEntity? = realm.where(UserEntity::class.java).findFirst()

        return user1!!.toUserPupils()
    }

    fun getSitters(): ArrayList<User>{

        val user1: UserEntity? = realm.where(UserEntity::class.java).findFirst()

        return user1!!.toUserSitters()
    }

    fun getPupil(email: String?) = realm.where(PupilUserEntity::class.java)
            .equalTo("email", email).findFirst().toUser()


    fun getSitter(email: String) = realm.where(SitterUserEntity::class.java)
            .equalTo("email", email).findFirst().toUser()

    fun closeRealm(): Unit = realm.close()

    fun saveToken(token: String): Unit {
        tokenRealm.executeTransaction {
            val oldToken = tokenRealm.where(TokenEntity::class.java)?.findFirst()?.token
            if(oldToken != token){
                tokenRealm.delete(TokenEntity::class.java)
                tokenRealm.copyToRealm(TokenEntity(token))
            }
        }
    }

    fun getToken(): String? {
        var token: String? = ""
        tokenRealm.executeTransaction {
            token = tokenRealm.where(TokenEntity::class.java)?.findFirst()?.token
        }
        return token
    }

    fun saveSettings(settings: Settings){
        tokenRealm.executeTransaction{
            tokenRealm.delete(SettingsEntity::class.java)
            tokenRealm.copyToRealm(settings.toSettingsEntity())
        }
    }

    fun getSettings(): Settings?{
        var settings: SettingsEntity? = SettingsEntity()
        tokenRealm.executeTransaction {
            settings = tokenRealm.where(SettingsEntity::class.java)?.findFirst()
        }
        return settings?.toSettings()
    }

    fun getSitters(email: String): ArrayList<User> {
        val user1: UserEntity? = realm.where(UserEntity::class.java).findFirst()
        val sitters = user1?.pupils?.find { it.email == email }?.toSitters()

        return sitters!!
    }

}