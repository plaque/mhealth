package plaque.mhealth.database.entities

import io.realm.RealmList
import io.realm.RealmObject
import plaque.mhealth.model.DateResultPair
import plaque.mhealth.model.Result

/**
 * Created by szymon on 01.11.17.
 */
open class ResultEntity(var title: String = "",
                   var results: RealmList<DateResultPairEntity> = RealmList()) : RealmObject(){

    fun toResult() = Result(title, this.toResultsList())

    private fun toResultsList(): ArrayList<DateResultPair>{
        val nResults = arrayListOf<DateResultPair>()
        results.forEach {
            nResults.add(it.toDateResultPair())
        }

        return nResults
    }
}