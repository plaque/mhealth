package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.CyclicNote
import java.time.DayOfWeek
import java.util.*

/**
 * Created by szymon on 08.10.17.
 */
open class CyclicNoteEntity(var days: RealmList<RealmString> = RealmList(), var hours: RealmList<RealmString> = RealmList(),
                            var content: String = "", var title: String = "",
                            var active: Boolean = false) : RealmObject(){

    fun toCyclicNoteDays(): List<String>{
        val cnDays = arrayListOf<String>()
        days.forEach {
            cnDays.add(it.string)
        }
        return cnDays
    }

    fun toCyclicNoteHours(): List<String>{
        val cnHours = arrayListOf<String>()
        days.forEach {
            cnHours.add(it.string)
        }
        return cnHours
    }

    fun toCyclicNote() = CyclicNote(this.toCyclicNoteDays(), this.toCyclicNoteHours(), this.content,
            this.title, this.active)
}