package plaque.mhealth.model

import io.realm.RealmList
import plaque.mhealth.database.entities.DateResultPairEntity
import plaque.mhealth.database.entities.ResultEntity
import plaque.mhealth.ui.adapters.AdapterConstants
import plaque.mhealth.ui.adapters.ViewType
import java.util.ArrayList

/**
 * Created by szymon on 01.11.17.
 */
class Result(val title: String, val results: ArrayList<DateResultPair>): ViewType {

    override fun getViewType(): Int = AdapterConstants.RESULTS

    fun toResultEntity() = ResultEntity(title, this.toResultsRealmList())

    private fun toResultsRealmList(): RealmList<DateResultPairEntity> {
        val rlResults = RealmList<DateResultPairEntity>()
        results.forEach{
            rlResults.add(it.toDateResultPairEntity())
        }
        return rlResults
    }
}