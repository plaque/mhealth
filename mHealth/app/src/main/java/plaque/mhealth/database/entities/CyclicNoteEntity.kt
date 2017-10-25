package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.model.CyclicNote
import java.time.DayOfWeek
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by szymon on 08.10.17.
 */
open class CyclicNoteEntity(var days: RealmList<RealmInt> = RealmList(), var hours: RealmList<RealmString> = RealmList(),
                            var content: String = "", var title: String = "",
                            var active: Boolean = false) : RealmObject(){

    fun toCyclicNoteDays(): ArrayList<Int>{
        val cnDays = arrayListOf<Int>()
        days.forEach {
            cnDays.add(it.id)
        }
        return cnDays
    }

    fun toCyclicNoteHours(): List<String>{
        val cnHours = arrayListOf<String>()
        hours.forEach {
            cnHours.add(it.string)
        }
        return cnHours
    }

    fun toCyclicNote() = CyclicNote(this.toCyclicNoteDays(), this.toCyclicNoteHours(), this.content,
            this.title, this.active)
}