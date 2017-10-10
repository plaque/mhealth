package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 10.10.17.
 */
open class PupilUserEntity(@PrimaryKey var email: String = "", var name: String? = "",
                           var surname: String? = "",
                           var ontNotes: RealmList<OneTimeNoteEntity>? = RealmList(),
                           var cnNotes: RealmList<CyclicNoteEntity>? = RealmList()): RealmObject()