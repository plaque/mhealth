package plaque.mhealth.database.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.User

/**
 * Created by szymon on 10.10.17.
 */
open class SitterUserEntity(var email: String = "", var name: String? = "",
                            var surname: String? = ""): RealmObject(){

    fun toUser() = User(this.email, this.name, this.surname, null, null, null, null, null)
}
