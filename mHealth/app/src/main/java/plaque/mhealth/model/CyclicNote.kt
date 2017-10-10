package plaque.mhealth.model

import io.realm.Realm
import io.realm.RealmList
import plaque.mhealth.commons.toRealmString
import plaque.mhealth.database.entities.CyclicNoteEntity
import plaque.mhealth.database.entities.RealmString
import java.time.DayOfWeek

class CyclicNote(var days: List<DayOfWeek>, var hours: List<String>, content: String, title: String,
                      active: Boolean): Note(content, title, active){

    fun toCyclicNoteEntity() = CyclicNoteEntity(this.toRealmDays(), this.toRealmHours(), this.content, this.title, this.active)

    fun toRealmHours(): RealmList<RealmString>{

        val realmHours = RealmList<RealmString>()
        hours.forEach {
            realmHours.add(it.toRealmString())
        }
        return realmHours
    }

    fun toRealmDays(): RealmList<RealmString>{
        var realmDays = RealmList<RealmString>()
        days.forEach {
            realmDays.add(it.toString().toRealmString())
        }
        return realmDays
    }
}