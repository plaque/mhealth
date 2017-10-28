package plaque.mhealth.model

import io.realm.RealmList
import plaque.mhealth.R
import plaque.mhealth.commons.toRealmString
import plaque.mhealth.database.entities.CyclicNoteEntity
import plaque.mhealth.database.entities.HourMinutePairEntity
import plaque.mhealth.database.entities.RealmInt
import plaque.mhealth.database.entities.RealmString
import plaque.mhealth.ui.adapters.AdapterConstants
import plaque.mhealth.ui.adapters.ViewType

class CyclicNote(var days: ArrayList<Int>, var hours: ArrayList<HourMinutePair>, var content: String, var title: String,
                      var active: Boolean): ViewType{

    override fun getViewType() = AdapterConstants.MEDS

    fun toCyclicNoteEntity() = CyclicNoteEntity(this.toRealmDays(), this.toRealmHours(), this.content, this.title, this.active)

    fun toRealmHours(): RealmList<HourMinutePairEntity>{

        val realmHours = RealmList<HourMinutePairEntity>()
        hours.forEach {
            realmHours.add(it.toHourMinutePairEntity())
        }
        return realmHours
    }

    fun toRealmDays(): RealmList<RealmInt>{
        var realmDays = RealmList<RealmInt>()
        days.forEach {
            realmDays.add(RealmInt(it))
        }
        return realmDays
    }

    fun daysToString(daysStrings: ArrayList<String>): String {
        var daysString = StringBuilder()
        days.forEach {
            when(it){
                1 -> daysString.append(daysStrings[0]).append(" ")
                2 -> daysString.append(daysStrings[1]).append(" ")
                3 -> daysString.append(daysStrings[2]).append(" ")
                4 -> daysString.append(daysStrings[3]).append(" ")
                5 -> daysString.append(daysStrings[4]).append(" ")
                6 -> daysString.append(daysStrings[5]).append(" ")
                7 -> daysString.append(daysStrings[6]).append(" ")
            }
        }

        return daysString.toString()
    }
}