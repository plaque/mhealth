package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 08.10.17.
 */
open class UserEntity(@PrimaryKey var email: String = "", var name: String? = "", var surname: String? = "",
                      var ontNotes: RealmList<OneTimeNoteEntity>? = RealmList(),
                      var cnNotes: RealmList<CyclicNoteEntity>? = RealmList(),
                      var sitters: RealmList<SitterUserEntity>? = RealmList(),
                      var pupils: RealmList<PupilUserEntity>? = RealmList(),
                      var sittersId: RealmList<RealmInt>? = RealmList(),
                      var pupilsId: RealmList<RealmInt>? = RealmList()): RealmObject()