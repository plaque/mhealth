package plaque.mhealth.database.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by szymon on 10.10.17.
 */
open class RealmInt(@PrimaryKey var realmId: String = UUID.randomUUID().toString(),
                    var id: Int = 0): RealmObject()