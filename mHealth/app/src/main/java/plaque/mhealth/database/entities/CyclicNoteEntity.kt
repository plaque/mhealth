package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import java.time.DayOfWeek

/**
 * Created by szymon on 08.10.17.
 */
open class CyclicNoteEntity(var days: RealmList<RealmString> = RealmList(), var hours: RealmList<RealmString> = RealmList(),
                            var content: String = "", var title: String = "",
                            var active: Boolean = false) : RealmObject()