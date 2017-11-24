package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.Result
import plaque.mhealth.model.User

/**
 * Created by szymon on 10.10.17.
 */
open class PupilUserEntity(var email: String = "", var name: String? = "",
                           var surname: String? = "", var phoneNumber: String? = "",
                           var cnNotes: RealmList<CyclicNoteEntity>? = RealmList(),
                           var realmSitterIds: RealmList<RealmInt>? = RealmList(),
                           var realmSitters: RealmList<SitterUserEntity>? = RealmList(),
                           var realmResults: RealmList<ResultEntity>? = RealmList()): RealmObject(){

    fun toUser() = User(this.email, this.name, this.surname, this.phoneNumber, this.toUserNotes(), this.toUserResults(),
            this.toSitters(), null, this.toSitterIds(), null)

    fun toSitters(): ArrayList<User> {
        val sitters = arrayListOf<User>()
        realmSitters?.forEach {
            sitters.add(it.toUser())
        }
        return sitters
    }

    fun toUserNotes(): ArrayList<CyclicNote>{
        val notes = arrayListOf<CyclicNote>()
        cnNotes?.forEach {
            notes.add(it.toCyclicNote())
        }
        return notes
    }

    fun toUserResults(): ArrayList<Result>{
        val results = arrayListOf<Result>()
        realmResults?.forEach {
            results.add(it.toResult())
        }
        return results
    }

    fun toSitterIds(): ArrayList<Int>{
        val sitterIds = arrayListOf<Int>()
        realmSitterIds?.forEach {
            sitterIds.add(it.id)
        }
        return  sitterIds
    }
}

