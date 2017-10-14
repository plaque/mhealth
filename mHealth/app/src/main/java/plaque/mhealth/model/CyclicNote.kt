package plaque.mhealth.model

import io.realm.Realm
import io.realm.RealmList
import plaque.mhealth.commons.toRealmString
import plaque.mhealth.database.entities.CyclicNoteEntity
import plaque.mhealth.database.entities.RealmString
import plaque.mhealth.ui.adapters.AdapterConstants
import plaque.mhealth.ui.adapters.ViewType

class CyclicNote(var days: List<String>, var hours: List<String>, var content: String, var title: String,
                      var active: Boolean): ViewType{

    override fun getViewType() = AdapterConstants.MEDS

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
            realmDays.add(it.toRealmString())
        }
        return realmDays
    }
}