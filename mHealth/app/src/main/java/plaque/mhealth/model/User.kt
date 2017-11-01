package plaque.mhealth.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import plaque.mhealth.database.entities.*
import plaque.mhealth.ui.adapters.AdapterConstants
import plaque.mhealth.ui.adapters.ViewType

/**
 * Created by szymon on 18.08.17.
 */
data class User(@PrimaryKey val email: String, val name: String?, val surname: String?,
                var notes: ArrayList<CyclicNote>?, var results: ArrayList<Result>?,
                var sitters: ArrayList<User>?, var pupils: ArrayList<User>?,
                var sittersId: ArrayList<Int>?, var pupilsId: ArrayList<Int>?): ViewType {

    override fun getViewType(): Int = AdapterConstants.PEOPLE

    private fun cyclicNotesToRealm(): RealmList<CyclicNoteEntity>{
        val cn = RealmList<CyclicNoteEntity>()
        this.notes?.forEach {
            cn.add(it.toCyclicNoteEntity())
        }
        return cn
    }

    private fun resultsToRealm(): RealmList<ResultEntity>{
        val realmResults = RealmList<ResultEntity>()
        this.results?.forEach{
            realmResults.add(it.toResultEntity())
        }
        return realmResults
    }

    private fun sittersToRealm(): RealmList<SitterUserEntity>{
        val realmSitters = RealmList<SitterUserEntity>()
        this.sitters?.forEach {

            realmSitters.add(it.toSitterUserEntity())

        }
        return realmSitters
    }

    private fun pupilsToRealm(): RealmList<PupilUserEntity>{
        val realmPupils = RealmList<PupilUserEntity>()
        this.pupils?.forEach {

            realmPupils.add(it.toPupilUserEntity())

        }
        return realmPupils
    }

    private fun sittersIdToRealm(): RealmList<RealmInt>{
        val realmSittersId = RealmList<RealmInt>()
        this.sittersId?.forEach{

            realmSittersId.add(RealmInt(it))

        }
        return realmSittersId
    }

    private fun pupilsIdToRealm(): RealmList<RealmInt>{
        val realmPupilsId = RealmList<RealmInt>()
        this.pupilsId?.forEach {

            realmPupilsId.add(RealmInt(it))

        }
        return realmPupilsId
    }

    fun toUserEntity() = UserEntity(this.email, this.name, this.surname,
            this.cyclicNotesToRealm(), this.resultsToRealm(), this.sittersToRealm(), this.pupilsToRealm(),
            this.sittersIdToRealm(), this.pupilsIdToRealm())

    private fun toPupilUserEntity() = PupilUserEntity(this.email, this.name, this.surname,
            this.cyclicNotesToRealm(), this.resultsToRealm())

    private fun toSitterUserEntity() = SitterUserEntity(this.email, this.name, this.surname)
}