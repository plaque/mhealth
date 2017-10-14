package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User

/**
 * Created by szymon on 10.10.17.
 */
open class PupilUserEntity(var email: String = "", var name: String? = "",
                           var surname: String? = "",
                           var cnNotes: RealmList<CyclicNoteEntity>? = RealmList()): RealmObject(){

    fun toUser() = User(this.email, this.name, this.surname, this.toUserNotes(), null, null, null, null)

    fun toUserNotes(): ArrayList<CyclicNote>{
        val notes = arrayListOf<CyclicNote>()
        cnNotes?.forEach {
            notes.add(it.toCyclicNote())
        }
        return notes
    }
}

