package plaque.mhealth.managers

import io.reactivex.Observable
import plaque.mhealth.model.CyclicNote
import java.time.DayOfWeek
import java.util.*

/**
 * Created by szymon on 14.09.17.
 */
class MedsManager {
    fun getMeds(): Observable<List<CyclicNote>>{
        return Observable.create {
            subscriber ->

            val meds = mutableListOf<CyclicNote>()

            for(i in 1..10){
                meds.add(CyclicNote(arrayListOf("Mon"), arrayListOf("15:30"), "author $i", "content $i", i%2 == 0))
            }
            subscriber.onNext(meds)
        }
    }
}