package plaque.mhealth.database.entities

import io.realm.RealmModel
import io.realm.RealmObject
import java.util.*

/**
 * Created by szymon on 08.10.17.
 */
open class OneTimeNoteEntity(var date: String = "", var content: String = "",
                             var title: String = "",
                             var active: Boolean = false) : RealmObject()