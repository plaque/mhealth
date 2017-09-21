package plaque.mhealth.Database

import io.realm.Realm

/**
 * Created by szymon on 21.09.17.
 */
class RealmService(val realm: Realm) {

    fun closeRealm(): Unit = realm.close()
}