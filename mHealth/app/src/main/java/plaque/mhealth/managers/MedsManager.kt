package plaque.mhealth.managers

import io.reactivex.Observable
import plaque.mhealth.model.Note
import plaque.mhealth.model.OneTimeNote
import java.util.*

/**
 * Created by szymon on 14.09.17.
 */
class MedsManager {
    fun getMeds(): Observable<List<Note>>{
        return Observable.create {
            subscriber ->

            val meds = mutableListOf<Note>()

            for(i in 1..10){
                meds.add(OneTimeNote(Calendar.getInstance(), "author $i", "content $i", i%2 == 0))
            }
            subscriber.onNext(meds)
        }
    }
}