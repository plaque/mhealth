package plaque.mhealth.Managers

import io.reactivex.Observable
import kotlinx.android.synthetic.main.content_meds.*
import plaque.mhealth.Adapters.NotesAdapter
import plaque.mhealth.Model.Note
import plaque.mhealth.Model.OneTimeNote
import java.util.*

/**
 * Created by szymon on 14.09.17.
 */
class MedsManager() {
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