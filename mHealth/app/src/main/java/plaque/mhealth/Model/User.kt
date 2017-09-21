package plaque.mhealth.Model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 18.08.17.
 */
data class User(@PrimaryKey val email: String, val name: String?, val surname: String?,
                var notes: RealmList<Note>?, var sitters: RealmList<User>?,
                var pupils: RealmList<User>?, var sittersId: RealmList<User>?,
                var pupilsId:RealmList<User>?) : RealmObject()