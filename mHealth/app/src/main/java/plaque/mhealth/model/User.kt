package plaque.mhealth.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by szymon on 18.08.17.
 */
data class User(@PrimaryKey val email: String, val name: String?, val surname: String?,
                var notes: ArrayList<Note>?, var sitters: ArrayList<User>?,
                var pupils: ArrayList<User>?, var sittersId: ArrayList<User>?,
                var pupilsId: ArrayList<User>?)