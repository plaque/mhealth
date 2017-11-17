package plaque.mhealth.database.entities

import io.realm.RealmObject

/**
 * Created by szymon on 17.11.17.
 */
open class TokenEntity(var token: String = ""): RealmObject() {
}