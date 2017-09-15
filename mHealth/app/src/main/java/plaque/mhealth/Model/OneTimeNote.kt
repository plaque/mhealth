package plaque.mhealth.Model

import java.util.*

/**
 * Created by szymon on 30.08.17.
 */
data class OneTimeNote(var date: Calendar, override var content: String, override var title: String,
                       override var active: Boolean) : Note(content, title, active){

}