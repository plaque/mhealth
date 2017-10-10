package plaque.mhealth.database.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 10.10.17.
 */
open class SitterUserEntity(@PrimaryKey var email: String = "", var name: String? = "",
                            var surname: String? = ""): RealmObject()