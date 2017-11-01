package plaque.mhealth.database.entities

import io.realm.RealmObject
import plaque.mhealth.model.DateResultPair

/**
 * Created by szymon on 01.11.17.
 */
open class DateResultPairEntity(var date: String = "", var result: Float = 0f): RealmObject() {

    fun toDateResultPair() = DateResultPair(date, result)
}