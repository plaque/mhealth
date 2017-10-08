package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 08.10.17.
 */
class UserEntity(@PrimaryKey val email: String, val name: String?, val surname: String?,
                 var notes: RealmList<NoteEntity>?, var sitters: RealmList<UserEntity>?,
                 var pupils: RealmList<UserEntity>?, var sittersId: RealmList<UserEntity>?,
                 var pupilsId: RealmList<UserEntity>?): RealmObject()