package plaque.mhealth.model

import plaque.mhealth.database.entities.DateResultPairEntity

/**
 * Created by szymon on 01.11.17.
 */
class DateResultPair(val date:String, val result:Float) {

    fun toDateResultPairEntity() = DateResultPairEntity(date, result)
}