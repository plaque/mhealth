package plaque.mhealth.database

import io.realm.Realm
import plaque.mhealth.database.entities.UserEntity
import plaque.mhealth.model.User

/**
 * Created by szymon on 21.09.17.
 */
class RealmService(val realm: Realm) {


    fun addUser(user: User) {

        val user1 = realm.where(UserEntity::class.java).equalTo("email", user.email).findFirst()
        if(user1 == null){
            realm.executeTransaction {
                val userEntity = user.toUserEntity()
                realm.copyToRealm(userEntity)
                System.out.println(userEntity)
            }
        }
    }

    fun getUser(){
        val user1: UserEntity = realm.where(UserEntity::class.java).findFirst()
        val user = user1.toUser()
    }

    fun closeRealm(): Unit = realm.close()
}