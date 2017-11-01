package plaque.mhealth.model

import plaque.mhealth.database.entities.HourMinutePairEntity
import plaque.mhealth.database.entities.RealmInt

/**
 * Created by szymon on 28.10.17.
 */
class HourMinutePair(val hour:Int, val minute:Int) {

    override fun toString(): String {

        var sHour = "$hour"
        var sMinute = "$minute"

        if(hour < 10) sHour = "0$hour"
        if(minute < 10) sMinute = "0$minute"

        return "$sHour:$sMinute"
    }

    fun toHourMinutePairEntity(): HourMinutePairEntity = HourMinutePairEntity(RealmInt(hour), RealmInt(minute))
}