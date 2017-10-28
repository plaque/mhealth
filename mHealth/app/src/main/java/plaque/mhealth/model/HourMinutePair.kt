package plaque.mhealth.model

import plaque.mhealth.database.entities.HourMinutePairEntity
import plaque.mhealth.database.entities.RealmInt

/**
 * Created by szymon on 28.10.17.
 */
class HourMinutePair(val hour:Int, val minute:Int) {
    override fun toString(): String = "$hour:$minute"

    fun toHourMinutePairEntity(): HourMinutePairEntity = HourMinutePairEntity(RealmInt(hour), RealmInt(minute))
}