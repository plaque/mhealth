package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.CyclicNote
import plaque.mhealth.model.User

/**
 * Created by szymon on 08.10.17.
 */
open class UserEntity(var email: String = "", var name: String? = "", var surname: String? = "",
                      var cnNotes: RealmList<CyclicNoteEntity>? = RealmList(),
                      var sitters: RealmList<SitterUserEntity>? = RealmList(),
                      var pupils: RealmList<PupilUserEntity>? = RealmList(),
                      var sittersId: RealmList<RealmInt>? = RealmList(),
                      var pupilsId: RealmList<RealmInt>? = RealmList()): RealmObject(){

    fun toUser() = User(this.email, this.name, this.surname, this.toUserNotes(), this.toUserSitters(),
            this.toUserPupils(), this.toUserSittersId(), this.toUserPupilsId())

    fun toUserNotes(): ArrayList<CyclicNote>{
        val notes = arrayListOf<CyclicNote>()
        cnNotes?.forEach {
            notes.add(it.toCyclicNote())
        }
        return notes
    }

    fun toUserSitters(): ArrayList<User>{
        val userSitters = arrayListOf<User>()
        sitters?.forEach {
            userSitters.add(it.toUser())
        }
        return userSitters
    }

    fun toUserPupils(): ArrayList<User>{
        val userPupils = arrayListOf<User>()
        pupils?.forEach {
            userPupils.add(it.toUser())
        }
        return userPupils
    }

    fun toUserPupilsId(): ArrayList<Int>{
        val userPupilsId = arrayListOf<Int>()
        pupilsId?.forEach {
            userPupilsId.add(it.id)
        }
        return userPupilsId
    }

    fun toUserSittersId(): ArrayList<Int>{
        val userSittersId = arrayListOf<Int>()
        sittersId?.forEach {
            userSittersId.add(it.id)
        }
        return userSittersId
    }





}


