package plaque.mhealth.database.entities

import io.realm.RealmObject
import plaque.mhealth.model.HourMinutePair

/**
 * Created by szymon on 28.10.17.
 */
open class HourMinutePairEntity(var hour: RealmInt = RealmInt(), var minute: RealmInt = RealmInt()): RealmObject() {

    fun toHourMinutePair(): HourMinutePair = HourMinutePair(hour.id, minute.id)

}